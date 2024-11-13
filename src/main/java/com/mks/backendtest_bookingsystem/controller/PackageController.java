package com.mks.backendtest_bookingsystem.controller;

import com.mks.backendtest_bookingsystem.dto.PackageDto;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.PackageService;
import com.mks.backendtest_bookingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/admin/create")
    public Response createPackage(@RequestBody PackageDto packageDto) {
        return packageService.createPackage(packageDto);
    }

    @GetMapping("/available")
    public Response getAvailablePackages(@RequestParam String country) {
        return packageService.getAvailablePackages(country);
    }

    @GetMapping("/user")
    public Response getUserPackages(@RequestHeader("Authorization") String authHeader) {
        return packageService.getUserPackages(extractEmailFromToken(authHeader));
    }

    @PostMapping("/purchase")
    public Response purchasePackage(
            @RequestParam Long userId,
            @RequestParam Long packageId) {
        return packageService.purchasePackage( userId, packageId);
    }

    private String extractEmailFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtUtil.extractUsername(token);
    }

}
