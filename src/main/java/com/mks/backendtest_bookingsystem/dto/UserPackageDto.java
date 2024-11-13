package com.mks.backendtest_bookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserPackageDto {
    private String packageName;
    private int remainingCredits;
    private boolean expired;
    private LocalDateTime expiredDate;
    private LocalDateTime purchasedDate;
    private String status;
}
