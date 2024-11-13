package com.mks.backendtest_bookingsystem.service.impl;

import com.mks.backendtest_bookingsystem.dto.ClassScheduleDto;
import com.mks.backendtest_bookingsystem.dto.UserBookingDto;
import com.mks.backendtest_bookingsystem.dto.UserPackageDto;
import com.mks.backendtest_bookingsystem.entity.*;
import com.mks.backendtest_bookingsystem.repository.*;
import com.mks.backendtest_bookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service("bookingService")
public class BooingServiceImpl  implements BookingService{

    @Autowired
    private ClassScheduleRepository classRepository;

    @Autowired
    private UserBookingRepository userBookingRepository;

    @Autowired
    private UserPackageRepository userPackageRepository;


    @Autowired
    private BSUserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private static final int MAX_BOOKINGS = 5;

    public List<ClassSchedule> getClassesByCountry(String country) {
        return classRepository.findByCountry(country);
    }

    public synchronized Response bookClass(Long classId, String userEmail) {

            BSUser user = userRepository.findUserByEmail(userEmail);
            ClassSchedule scheduledClass = classRepository.findById(classId).orElseThrow(() -> new RuntimeException("Class not found"));
            List<UserPackage> userPackages = userPackageRepository.findByUser(user);

            UserPackage matchingPackage = userPackages.stream()
                    .filter(p -> p.getPackageDetails().getCountry().equals(scheduledClass.getCountry()) && p.getRemainingCredits() >= scheduledClass.getRequiredCredits())
                    .findFirst()
                    .orElse(null);

            if (matchingPackage == null) {
                return new Response(false, "No valid package found for booking this class in " + scheduledClass.getCountry());
            }

            if (hasOverlapBooking(user, scheduledClass)) {
                return new Response(false, "Booking overlaps with an existing class.");
            }

            synchronized (scheduledClass) {
                int currentClassBookings = userBookingRepository.countByScheduledClassAndIsWaitlistedFalseAndStatusContaining(scheduledClass, "ACTIVE");

                if (scheduledClass.isFull(currentClassBookings)) {
                    // Class is full, add to waitlist
                    bookUserForClass(user, scheduledClass, matchingPackage, true);
                    return new Response(true, "Added to waitlist.");
                } else {
                    // Book the class if there's space
                    bookUserForClass(user, scheduledClass, matchingPackage, false);
                    return new Response(true, "Class booked successfully.");
                }
            }

    }

    private boolean hasOverlapBooking(BSUser user, ClassSchedule newClass) {
        List<UserBooking> existingBookings = userBookingRepository.findByUserAndIsWaitlistedFalseAndStatusIsContaining(user, "ACTIVE");

        for (UserBooking booking : existingBookings) {
            if (newClass.getStartTime().isBefore(booking.getScheduledClass().getEndTime()) &&
                    newClass.getEndTime().isAfter(booking.getScheduledClass().getStartTime())) {
                return true;
            }
        }
        return false;
    }

    private void bookUserForClass(BSUser user, ClassSchedule scheduledClass, UserPackage matchingPackage, boolean waitlist) {

        matchingPackage.setRemainingCredits(matchingPackage.getRemainingCredits() - scheduledClass.getRequiredCredits());
        userPackageRepository.save(matchingPackage);

        UserBooking booking = new UserBooking();
        booking.setUser(user);
        booking.setUserPackage(matchingPackage);
        booking.setScheduledClass(scheduledClass);
        booking.setBookingTime(LocalDateTime.now());
        booking.setWaitlisted(waitlist);
        booking.setStatus("ACTIVE");
        userBookingRepository.save(booking);
    }
    

    public Response cancelBooking(Long bookingId) {
        UserBooking booking = userBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        ClassSchedule classSchedule = booking.getScheduledClass();
        LocalDateTime now = LocalDateTime.now();
        UserPackage userPackage = booking.getUserPackage();

        // Check if the booking is already cancel
        if(booking.getStatus().equals("CANCEL")){
            return new Response(true, "Booking is already canceled.");
        }

        // Check if the cancellation is more than 4 hours before class start time
        if (now.isBefore(classSchedule.getStartTime().minusHours(4))) {
            // Refund credits
            userPackage.setRemainingCredits(userPackage.getRemainingCredits() + classSchedule.getRequiredCredits());
            userPackageRepository.save(userPackage);
        }

        if (!booking.isWaitlisted()) {
            // Find the next waitlisted user to add to booked list
            List<UserBooking> waitlistedUsers = userBookingRepository.findByScheduledClassAndIsWaitlistedTrue(booking.getScheduledClass());
            if (!waitlistedUsers.isEmpty()) {
                UserBooking waitlistedUser = waitlistedUsers.stream().findFirst()
                                .orElse(null);
                waitlistedUser.setWaitlisted(false);
                userBookingRepository.save(waitlistedUser);
            }
        }
        booking.setStatus("CANCEL");
        booking.setCancellationTime(LocalDateTime.now());
        userBookingRepository.save(booking);
        return new Response(true, "Booking canceled.");
    }

    public Response checkIn(Long bookingId) {
        UserBooking booking = userBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Check the class has started
        if (LocalDateTime.now().isBefore(booking.getScheduledClass().getStartTime())) {
            return new Response(false, "Cannot check in before the class starts.");
        }

        booking.setCheckedIn(true);
        userBookingRepository.save(booking);
        return new Response(true, "Checked in successfully.");
    }

    public Response getUserClasses(String email) {
        List<UserBookingDto> result = toGetUserClasses(email);
        Response response = new Response<>(true, "success");
        response.setData(result);
        return response;
    }

    public List<UserBookingDto> toGetUserClasses(String email) {
        BSUser user = userRepository.findUserByEmail(email);
        List<UserBooking> userBookings = userBookingRepository.findByUserAndStatusIsContaining(user, "ACTIVE");

        List<UserBookingDto> result = new ArrayList<>();
        for (UserBooking userBooking : userBookings) {
            UserBookingDto dto = new UserBookingDto();
            dto.setPackageName(userBooking.getUserPackage().getPackageDetails().getName());
            dto.setClassName(userBooking.getScheduledClass().getClassName());
            dto.setStartTime(userBooking.getScheduledClass().getStartTime());
            dto.setEndTime(userBooking.getScheduledClass().getEndTime());
            dto.setCountry(userBooking.getScheduledClass().getCountry());
            dto.setStatus(userBooking.getStatus());
            result.add(dto);
        }
        return result;
    }

    @Scheduled(fixedRate = 3600000)
    public void processWaitlistRefunds() {
        List<ClassSchedule> completedClasses = classRepository.findClassScheduleByEndTimeBefore(LocalDateTime.now());

        for (ClassSchedule classSchedule : completedClasses) {
            refundWaitlistedCredits(classSchedule);
        }
    }

    public void refundWaitlistedCredits(ClassSchedule classSchedule) {
        List<UserBooking> waitlistedBookings = userBookingRepository.findByScheduledClassAndIsWaitlistedTrue(classSchedule);

        for (UserBooking booking : waitlistedBookings) {
            UserPackage userPackage = booking.getUserPackage();
            userPackage.setRemainingCredits(userPackage.getRemainingCredits() + classSchedule.getRequiredCredits());
            userPackageRepository.save(userPackage);

            booking.setWaitlisted(false);
            booking.setStatus("CANCEL");
            userBookingRepository.save(booking);
        }
    }

}
