package com.pradana.notes.services;

import javax.validation.Valid;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pradana.notes.config.EncryptionConfig;
import com.pradana.notes.dto.UserDto;
import com.pradana.notes.repositories.UserRepository;
import com.pradana.notes.templates.UserResponse;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private ApplicationContext context = new AnnotationConfigApplicationContext(EncryptionConfig.class);

    /// Register user
    /// return Registered user
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        StrongPasswordEncryptor strongPasswordEncryptor = context.getBean("strongPasswordEncryptor",
                StrongPasswordEncryptor.class);

        String encryptedPassword = strongPasswordEncryptor.encryptPassword(userDto.getPassword());

        final UserDto user = new UserDto();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encryptedPassword);

        final UserResponse userResponse = new UserResponse();
        userResponse.setStatus("OK");
        userResponse.setResult(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
