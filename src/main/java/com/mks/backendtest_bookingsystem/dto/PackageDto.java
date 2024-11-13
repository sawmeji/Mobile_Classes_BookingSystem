package com.mks.backendtest_bookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PackageDto {
    private String name;
    private String country;
    private int credits;
    private int price;
    private LocalDateTime expirationDate;
}
