package com.mks.backendtest_bookingsystem.dto;

import com.mks.backendtest_bookingsystem.entity.BSUser;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BSUserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String role;

    public BSUserDto(BSUser user) {
        setId(user.getId());
        setName(user.getName());
        setEmail(user.getEmail());
    }
}
