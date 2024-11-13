package com.mks.backendtest_bookingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "waitlist")
public class WaitList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BSUser user;

    @ManyToOne
    private ClassSchedule scheduledClass;

    @Column(name = "wait_listed")
    private boolean waitlisted;

    private LocalDateTime waitlistTime;
}
