package com.mks.backendtest_bookingsystem.controller;

import com.mks.backendtest_bookingsystem.entity.ClassSchedule;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.BookingService;
import com.mks.backendtest_bookingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/classes")
    public ResponseEntity<List<ClassSchedule>> getClasses(@RequestParam String country) {
        return ResponseEntity.ok(bookingService.getClassesByCountry(country));
    }

    @PostMapping("/book")
    public Response bookClass(@RequestParam Long classId, @RequestHeader("Authorization") String authHeader) {
        String email = extractEmailFromToken(authHeader);
        return bookingService.bookClass(classId, email);
    }

    @GetMapping("/user")
    public Response getUserClasses(@RequestHeader("Authorization") String authHeader) {
        return bookingService.getUserClasses(extractEmailFromToken(authHeader));
    }

    @PostMapping("/cancel")
    public Response cancelBooking(@RequestParam Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @PostMapping("/checkin")
    public Response checkIn(@RequestParam Long bookingId) {
        return bookingService.checkIn(bookingId);
    }

    private String extractEmailFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtUtil.extractUsername(token);
    }

}
