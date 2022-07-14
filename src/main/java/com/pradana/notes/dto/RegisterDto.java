package com.pradana.notes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterDto {
    @NotNull(message = "Nama harus di isi")
    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(min = 4, message = "Nama hanya boleh di isi min 4 karakter")
    private String username;

    @NotNull(message = "Email harus di isi")
    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull(message = "Password harus di isi")
    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password minimal 8 karakter")
    private String password;
}
