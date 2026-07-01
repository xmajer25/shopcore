package com.xmajer.shopcore.authservice.service;

import com.xmajer.shopcore.authservice.data.enums.UserRole;
import com.xmajer.shopcore.authservice.data.model.User;
import com.xmajer.shopcore.authservice.data.repository.UserRepository;
import com.xmajer.shopcore.authservice.dto.request.AdminCreateUserRequest;
import com.xmajer.shopcore.authservice.dto.request.LoginUserRequest;
import com.xmajer.shopcore.authservice.dto.request.RegisterUserRequest;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.exception.UserEmailAlreadyRegisteredException;
import com.xmajer.shopcore.authservice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtSer


    public UserResponse registerUser(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new UserEmailAlreadyRegisteredException(request.email());
        }

        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setUserRole(UserRole.CUSTOMER);

        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse adminCreateUser(AdminCreateUserRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new UserEmailAlreadyRegisteredException(request.email());
        }

        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse loginUser(LoginUserRequest request) {

    }
}
