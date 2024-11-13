package com.mks.backendtest_bookingsystem.service;

import com.mks.backendtest_bookingsystem.dto.PackageDto;
import com.mks.backendtest_bookingsystem.dto.UserPackageDto;
import com.mks.backendtest_bookingsystem.entity.Package;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.impl.PackageServiceImpl;

import java.util.List;

public interface PackageService {

    public Response createPackage(PackageDto packageDto);

    public Response getAvailablePackages(String country);

    public Response getUserPackages(String email);

    public Response purchasePackage(Long userId, Long packageId);

}
