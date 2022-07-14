package com.pradana.notes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
    @NotNull(message = "Email harus di isi")
    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull(message = "Password harus di isi")
    @NotBlank(message = "Password tidak boleh kosong")
    private String password;
}
