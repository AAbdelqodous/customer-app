package com.elite.customer_app.mapper;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.dto.UserDto;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.model.UserEntity;

public class UserMapper {

    public static UserEntity mapToUserEntity(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        return UserEntity.builder()
                .username( userDto.getUsername())
                .password( userDto.getPassword())
                .email( userDto.getEmail())
                .build();
    }
}
