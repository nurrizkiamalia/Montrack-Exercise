package com.amalia.demoJPA.users.service;

import com.amalia.demoJPA.users.dto.ForgotPasswordDTO;
import com.amalia.demoJPA.users.dto.ProfileRequestDTO;
import com.amalia.demoJPA.users.dto.RegisterRequestDTO;
import com.amalia.demoJPA.users.entity.Users;

import java.util.List;

public interface UserService {

    Users findByEmail(String email);
    List<Users> findAll();

    Users findById(Long id);
    Users register(RegisterRequestDTO user);
    void deleteById(Long id);
    Users profile();

    Users forgotPassword(ForgotPasswordDTO forgotPasswordRequestDTO);
    ProfileRequestDTO updateProfile(ProfileRequestDTO profileRequestDTO);
    ProfileRequestDTO getProfile();

}