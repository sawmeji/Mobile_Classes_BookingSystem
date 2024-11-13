package com.mks.backendtest_bookingsystem.service.impl;

import com.mks.backendtest_bookingsystem.dto.PackageDto;
import com.mks.backendtest_bookingsystem.dto.UserPackageDto;
import com.mks.backendtest_bookingsystem.entity.*;
import com.mks.backendtest_bookingsystem.entity.Package;
import com.mks.backendtest_bookingsystem.repository.BSUserRepository;
import com.mks.backendtest_bookingsystem.repository.PackageRepository;
import com.mks.backendtest_bookingsystem.repository.PurchaseHistoryRepository;
import com.mks.backendtest_bookingsystem.repository.UserPackageRepository;
import com.mks.backendtest_bookingsystem.service.PackageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private UserPackageRepository userPackageRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    private BSUserRepository bsUserRepository;


    public void save(Package pack) {
        packageRepository.save(pack);
    }

    public Response createPackage(PackageDto packageDto) {
        Package newPackage = new Package();
        newPackage.setName(packageDto.getName());
        newPackage.setCountry(packageDto.getCountry());
        newPackage.setCredits(packageDto.getCredits());
        newPackage.setPrice(packageDto.getPrice());
        newPackage.setExpirationDate(packageDto.getExpirationDate());
        save(newPackage);

        return new Response(true, "Create Package successful!");
    }

    public Response getAvailablePackages(String country) {
        List<Package> result = packageRepository.findByCountry(country);
        Response response = new Response<>(true, "success");
        response.setData(result);
        return response;
    }
    public Response getUserPackages(String email) {
        List<UserPackageDto> result = toGetUserPackages(email);
        Response response = new Response<>(true, "success");
        response.setData(result);
        return response;
    }

    public List<UserPackageDto> toGetUserPackages(String email) {
        BSUser user = bsUserRepository.findUserByEmail(email);
        List<UserPackage> userPackages = userPackageRepository.findByUser(user);

        List<UserPackageDto> result = new ArrayList<>();
        for (UserPackage userPackage : userPackages) {
            UserPackageDto dto = new UserPackageDto();
            dto.setPackageName(userPackage.getPackageDetails().getName());
            dto.setRemainingCredits(userPackage.getRemainingCredits());
            dto.setExpired(userPackage.isExpired());
            dto.setExpiredDate(userPackage.getExpirationDate());
            dto.setPurchasedDate(userPackage.getPurchaseDate());
            dto.setStatus(userPackage.getStatus());
            result.add(dto);
        }
        return result;
    }

    public Response purchasePackage(Long userId, Long packageId){
        BSUser user = bsUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Package pack = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        toPurchasePackage(user, pack);
        return new Response(true, "Create Package successful!");
    }
    @Transactional
    public UserPackage toPurchasePackage(BSUser user, Package pack) {
        // Create UserPackage record
        UserPackage userPackage = new UserPackage();
        userPackage.setUser(user);
        userPackage.setPackageDetails(pack);
        userPackage.setRemainingCredits(pack.getCredits());
        userPackage.setExpirationDate(pack.getExpirationDate());
        userPackage.setPurchaseDate(LocalDateTime.now());
        userPackage.setStatus("ACTIVE");
        userPackageRepository.save(userPackage);

        // Create PurchaseHistory record
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUser(user);
        purchaseHistory.setPack(pack);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistory.setAmount(pack.getPrice());
        purchaseHistoryRepository.save(purchaseHistory);

        return userPackage;
    }




}
