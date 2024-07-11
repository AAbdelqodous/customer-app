package com.elite.customer_app.dto;

import com.elite.customer_app.model.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {

    private Long id;

    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    private String lastName;

    @Email(message = "Email should satisfy a specific pattern")
    private String email;

    private UserEntity createdBy;

    private List<OrderDto> orders;

}
