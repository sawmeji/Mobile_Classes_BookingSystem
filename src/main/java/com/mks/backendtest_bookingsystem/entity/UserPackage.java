package com.mks.backendtest_bookingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_package")
public class UserPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BSUser user;

    @ManyToOne
    private Package packageDetails;

    private int remainingCredits;
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate;
    private boolean expired;
    private String status;

    // Check expiration
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(packageDetails.getExpirationDate());
    }
}
