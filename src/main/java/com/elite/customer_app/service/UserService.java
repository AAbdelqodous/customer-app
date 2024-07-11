package com.elite.customer_app.service;

import com.elite.customer_app.dto.UserDto;
import com.elite.customer_app.model.UserEntity;

public interface UserService {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    void save(UserDto userDto);
}
