package com.mks.backendtest_bookingsystem.service.impl;

import com.mks.backendtest_bookingsystem.dto.BSUserDto;
import com.mks.backendtest_bookingsystem.dto.ChangePasswordDto;
import com.mks.backendtest_bookingsystem.dto.LoginRequest;
import com.mks.backendtest_bookingsystem.entity.BSUser;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.repository.BSUserRepository;
import com.mks.backendtest_bookingsystem.service.BSUserService;
import com.mks.backendtest_bookingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service("bsuserService")
public class BSUserServiceImpl implements BSUserService {

    @Autowired
    BSUserRepository bsuserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void save(BSUser user) {
        bsuserRepository.save(user);
    }

    public Response registerUser(BSUserDto dto) {
        BSUser user = new BSUser();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setVerified(false);
        user.setRole(dto.getRole().isEmpty()? "MEMBER" : dto.getRole());

        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);
        save(user);

        boolean emailSent = SendVerifyEmail(user.getEmail(), verificationToken);
        if (emailSent) {
            return new Response(true, "Registration successful! Please verify your email.");
        } else {
            return new Response(false, "Error sending verification email.");
        }
    }

    public boolean SendVerifyEmail(String email, String token) {
        System.out.println("Mock email sent to: " + email);
        System.out.println("Verification Token: " + token);
        return true;
    }

    public boolean verifyUser(String token) {
        Optional<BSUser> userOpt = Optional.ofNullable(bsuserRepository.findByVerificationToken(token));
        if (userOpt.isPresent()) {
            BSUser user = userOpt.get();
            user.setVerified(true);
            user.setVerificationToken(null);
            save(user);
            return true;
        } else {
            return false;
        }
    }

    public Response login(LoginRequest loginRequest) throws Exception {
        String token = authenticateUser(loginRequest);
        if (token != null) {
            return new Response(true, token);
        } else {
            return new Response(false, "စကား၀ှက်မှားနေပါသည်");
        }
    }


    public String authenticateUser(LoginRequest loginRequest) throws Exception {
        Optional<BSUser> userOpt = Optional.ofNullable(bsuserRepository.findUserByEmail(loginRequest.getEmail()));
        if (userOpt.isPresent()) {
            BSUser user = userOpt.get();
            if (!user.isVerified()) {
                throw new Exception("Email not verified. Please check your inbox.");
            }
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user.getEmail());
            }
        }
        return null;
    }

    public BSUserDto getUserProfile(String email) {
        BSUser user = bsuserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new BSUserDto(user);
    }

    public Response changePassword(ChangePasswordDto request) {
        BSUser user = bsuserRepository.findUserByEmail(request.getEmail());
        if (user != null && passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            bsuserRepository.save(user);
            return new Response(true, "Password changed successfully");
        }
        return new Response(false, "Current password is incorrect");
    }

    public Response requestPasswordReset(String email) {
        BSUser user = bsuserRepository.findUserByEmail(email);
        if (user != null) {
            String resetToken = UUID.randomUUID().toString();
            user.setVerificationToken(resetToken);
            bsuserRepository.save(user);
            return SendVerifyEmailForReset(user.getEmail(), resetToken);
        }
        return new Response(false, "Error sending password reset email");
    }

    public Response SendVerifyEmailForReset(String email, String token) {
        System.out.println("Password reset email sent to: " + email);
        System.out.println("Reset Token: " + token);
        return new Response(true, "Password reset email sent");
    }

    public Response resetPassword(String token, String newPassword){
        boolean success = resetPass(token, newPassword);
        if (success) {
            return new Response(true, "Password reset successful");
        } else {
            return new Response(false, "Invalid or expired token");
        }
    }
    public boolean resetPass(String token, String newPassword) {
        Optional<BSUser> userOpt = Optional.ofNullable(bsuserRepository.findByVerificationToken(token));
        if (userOpt.isPresent()) {
            BSUser user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setVerificationToken(null);
            bsuserRepository.save(user);
            return true;
        }
        return false;
    }

}
