package com.pradana.notes.templates;

import com.pradana.notes.dto.UserDto;

import lombok.Data;

@Data
public class UserResponse {
    private String status;
    private UserDto result;
}
