package com.mks.backendtest_bookingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "class_schedule")
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String description;
    private String country;
    private int maxCapacity;
    private int requiredCredits;
    private int availableSlots;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Check if the class is full
    public boolean isFull(int currentBookings) {
        return currentBookings >= maxCapacity;
    }

    public ClassSchedule(){}
}
