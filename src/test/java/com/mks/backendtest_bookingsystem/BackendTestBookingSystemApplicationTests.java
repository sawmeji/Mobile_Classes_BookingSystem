package com.mks.backendtest_bookingsystem;

import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendTestBookingSystemApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private BookingService bookingService;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private final Long classId = 1L;

    @BeforeEach
    public void setUp() {
        // Reset the Redis cache before each test to ensure consistent results
        redisTemplate.delete("class:" + classId + ":slots");
    }

    @Test
    public void testConcurrentBookings() throws InterruptedException {
        int numThreads = 10; // Number of users trying to book concurrently
        int maxSlots = 5; // Expected maximum slots

        // Use a CountDownLatch to start all threads at the same time
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<Response> responses = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    latch.await(); // Wait for the latch to start all threads at once
                    Response response = bookingService.bookClass(classId, "user" + Thread.currentThread().getId() + "@example.com");
                    synchronized (responses) {
                        responses.add(response);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Release all threads at once
        latch.countDown();

        // Wait for all tasks to complete
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }

        // Check how many successful bookings were made
        long successfulBookings = responses.stream().filter(Response::isStatus).count();
        long failedBookings = responses.size() - successfulBookings;

        // Assert that no more than the maximum slots were booked
        assertEquals(maxSlots, successfulBookings, "Only up to 5 users should be able to book successfully.");
        assertEquals(numThreads - maxSlots, failedBookings, "The remaining users should have received a failure response.");
    }

}
