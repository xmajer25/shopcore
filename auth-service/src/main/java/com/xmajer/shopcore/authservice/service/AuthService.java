package com.xmajer.shopcore.authservice.service;

import com.xmajer.shopcore.authservice.data.enums.UserRole;
import com.xmajer.shopcore.authservice.data.model.User;
import com.xmajer.shopcore.authservice.data.repository.UserRepository;
import com.xmajer.shopcore.authservice.dto.request.AdminCreateUserRequest;
import com.xmajer.shopcore.authservice.dto.request.LoginUserRequest;
import com.xmajer.shopcore.authservice.dto.request.RegisterUserRequest;
import com.xmajer.shopcore.authservice.dto.response.AuthResponse;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.exception.UserEmailAlreadyRegisteredException;
import com.xmajer.shopcore.authservice.exception.UserNotFoundException;
import com.xmajer.shopcore.authservice.exception.UserNotFoundType;
import com.xmajer.shopcore.authservice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


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

    public AuthResponse loginUser(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password."));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                "Bearer",
                jwtService.getExpirationSeconds(),
                userMapper.toResponse(user)
        );
    }
}
