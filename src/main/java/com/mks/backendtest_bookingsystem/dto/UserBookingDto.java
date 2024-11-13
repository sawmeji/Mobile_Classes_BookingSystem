package com.mks.backendtest_bookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserBookingDto {

    private String packageName;
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String country;
    private boolean isWaitlisted;
    private LocalDateTime bookingTime;
    private String status;
}
