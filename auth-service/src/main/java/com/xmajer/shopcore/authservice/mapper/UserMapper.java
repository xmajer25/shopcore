package com.xmajer.shopcore.authservice.mapper;

import com.xmajer.shopcore.authservice.data.model.User;
import com.xmajer.shopcore.authservice.dto.request.AdminCreateUserRequest;
import com.xmajer.shopcore.authservice.dto.request.LoginUserRequest;
import com.xmajer.shopcore.authservice.dto.request.RegisterUserRequest;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(RegisterUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(LoginUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(AdminCreateUserRequest request);

    UserResponse toResponse(User user);
}
