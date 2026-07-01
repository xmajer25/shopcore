package com.xmajer.shopcore.authservice.service;

import com.xmajer.shopcore.authservice.data.model.User;
import com.xmajer.shopcore.authservice.data.repository.UserRepository;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
