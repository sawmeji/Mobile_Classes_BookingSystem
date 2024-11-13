package com.mks.backendtest_bookingsystem.repository;

import com.mks.backendtest_bookingsystem.dto.BSUserDto;
import com.mks.backendtest_bookingsystem.entity.BSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BSUserRepository extends JpaRepository<BSUser, Long>, JpaSpecificationExecutor<BSUser> {
    BSUser findUserByEmail(String email);
    BSUser findByVerificationToken(String token);
}
