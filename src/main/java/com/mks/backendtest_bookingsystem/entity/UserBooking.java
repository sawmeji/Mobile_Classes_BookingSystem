package com.mks.backendtest_bookingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_booking")
public class UserBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BSUser user;

    @ManyToOne
    private ClassSchedule scheduledClass;

    @ManyToOne
    private UserPackage userPackage;

    @Column(nullable = false)
    private boolean checkedIn;

    @Column(nullable = false)
    private boolean isWaitlisted;
    private LocalDateTime cancellationTime;
    private LocalDateTime bookingTime;
    private String status;

    public  UserBooking(){}


}
