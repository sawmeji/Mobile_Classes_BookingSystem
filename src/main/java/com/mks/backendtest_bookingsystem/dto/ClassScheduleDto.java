package com.mks.backendtest_bookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassScheduleDto {
    private String className;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;
    private String country;
}
