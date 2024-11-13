package com.mks.backendtest_bookingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "bs_user")
public class BSUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    private String verificationToken;

    @Column(nullable = false)
    private boolean isVerified = false;



    private String role;

    public BSUser() {}

    public BSUser(Long id, String name, String email, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isVerified = isVerified;
    }
}
