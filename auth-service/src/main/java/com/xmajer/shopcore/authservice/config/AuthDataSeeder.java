package com.xmajer.shopcore.authservice.config;

import com.xmajer.shopcore.authservice.data.enums.UserRole;
import com.xmajer.shopcore.authservice.data.model.User;
import com.xmajer.shopcore.authservice.data.repository.UserRepository;
import lombok.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthDataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthDataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        String adminEmail = "admin@shopcore.local";

        if (userRepository.existsByEmail(adminEmail)) {
            return;
        }

        User admin = new User();
        admin.setEmail(adminEmail);
        admin.setPasswordHash(passwordEncoder.encode("Admin12345"));
        admin.setUserRole(UserRole.ADMIN);

        userRepository.save(admin);
    }
}