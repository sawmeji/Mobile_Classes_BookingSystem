package com.mks.backendtest_bookingsystem.controller;

import com.mks.backendtest_bookingsystem.dto.BSUserDto;
import com.mks.backendtest_bookingsystem.dto.ChangePasswordDto;
import com.mks.backendtest_bookingsystem.dto.LoginRequest;
import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.BSUserService;
import com.mks.backendtest_bookingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BSUserController {

    @Autowired
    private BSUserService bsUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Response registerUser(@RequestBody BSUserDto dto) throws Exception {
        return bsUserService.registerUser(dto);
    }

    @PostMapping("/verify")
    public Response verifyUser(@RequestParam("token") String token) {
        boolean isVerified = bsUserService.verifyUser(token);
        if (isVerified) {
            return new Response(true, "Email verified successfully!");
        } else {
            return new Response(false, "Invalid or expired verification token.");
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest) throws Exception {
        return bsUserService.login(loginRequest);
    }

    @GetMapping("/profile")
    public BSUserDto getProfile(@RequestHeader("Authorization") String authHeader) {
        return bsUserService.getUserProfile(extractEmailFromToken(authHeader));
    }

    @PostMapping("/change-password")
    public Response changePassword(@RequestBody ChangePasswordDto request) {
        return bsUserService.changePassword(request);
    }

    @PostMapping("/request-reset-password")
    public Response requestPasswordReset(@RequestParam("email") String email) {
        return bsUserService.requestPasswordReset(email);
    }

    @PostMapping("/reset-password")
    public Response resetPassword(@RequestParam("token") String token,
                                  @RequestParam("newPassword") String newPassword) {
        return bsUserService.resetPassword(token, newPassword);
    }

    private String extractEmailFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtUtil.extractUsername(token);
    }
}
