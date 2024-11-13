package com.mks.backendtest_bookingsystem.service;

import com.mks.backendtest_bookingsystem.dto.BSUserDto;
import com.mks.backendtest_bookingsystem.dto.ChangePasswordDto;
import com.mks.backendtest_bookingsystem.dto.LoginRequest;
import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.repository.BSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BSUserService{

    void save(BSUser user);

    Response registerUser(BSUserDto dto);

    public Response login(LoginRequest loginRequest) throws Exception;
    public String authenticateUser(LoginRequest loginRequest) throws Exception;

    public boolean verifyUser(String token);

    public BSUserDto getUserProfile(String email);

    public Response changePassword(ChangePasswordDto request);

    public Response requestPasswordReset(String email);

    public Response resetPassword(String token, String newPassword);
}
