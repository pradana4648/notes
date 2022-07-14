package com.pradana.notes.services;

import java.util.Optional;

import javax.validation.Valid;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pradana.notes.config.EncryptionConfig;
import com.pradana.notes.dto.LoginDto;
import com.pradana.notes.dto.RegisterDto;
import com.pradana.notes.pojo.User;
import com.pradana.notes.repositories.UserRepository;
import com.pradana.notes.templates.UserResponse;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private ApplicationContext context = new AnnotationConfigApplicationContext(EncryptionConfig.class);

    /// Register user
    /// return Registered user
    public ResponseEntity<UserResponse<User>> registerUser(@Valid RegisterDto userDto) {
        StrongPasswordEncryptor strongPasswordEncryptor = context.getBean("strongPasswordEncryptor",
                StrongPasswordEncryptor.class);

        String encryptedPassword = strongPasswordEncryptor.encryptPassword(userDto.getPassword());

        final User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encryptedPassword);

        final UserResponse<User> userResponse = new UserResponse<>();
        userResponse.setStatus("ok");
        userResponse.setMessage("success created account");
        userResponse.setResult(userRepository.save(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    public ResponseEntity<UserResponse<User>> loginUser(@Valid LoginDto loginDTO) {
        StrongPasswordEncryptor strongPasswordEncryptor = context.getBean("strongPasswordEncryptor",
                StrongPasswordEncryptor.class);

        Optional<User> userResult = userRepository.findByEmail(loginDTO.getEmail());
        if (userResult.isPresent()) {
            User user = userResult.get();

            // Jika password sama
            if (strongPasswordEncryptor.checkPassword(loginDTO.getPassword(), user.getPassword())) {
                final UserResponse<User> userResponse = new UserResponse<>();
                userResponse.setStatus("ok");
                userResponse.setMessage("success");
                userResponse.setResult(user);

                return ResponseEntity.status(HttpStatus.OK).body(userResponse);
            } else {
                final UserResponse<User> userResponse = new UserResponse<>();
                userResponse.setStatus("error");
                userResponse.setMessage("password is not match to our server");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
            }

        } else {
            final UserResponse<User> userResponse = new UserResponse<>();
            userResponse.setStatus("error");
            userResponse.setMessage("email is not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
    }
}
