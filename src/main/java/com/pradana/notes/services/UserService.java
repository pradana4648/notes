// package com.pradana.notes.services;

// import java.util.Optional;

// import javax.validation.Valid;

// import org.jasypt.util.password.StrongPasswordEncryptor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import
// org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.pradana.notes.config.EncryptionConfig;
// import com.pradana.notes.controller.v1.request.LoginRequest;
// import com.pradana.notes.controller.v1.request.RegisterRequest;
// import com.pradana.notes.dto.model.UserDto;
// import com.pradana.notes.dto.response.UserResponse;
// import com.pradana.notes.repositories.UserRepository;

// @Service
// public class UserService {
// @Autowired
// private UserRepository userRepository;

// private ApplicationContext context = new
// AnnotationConfigApplicationContext(EncryptionConfig.class);

// /// Register user
// /// return Registered user
// public ResponseEntity<UserResponse<UserDto>> registerUser(@Valid
// RegisterRequest userDto) {
// StrongPasswordEncryptor strongPasswordEncryptor =
// context.getBean("strongPasswordEncryptor",
// StrongPasswordEncryptor.class);

// String encryptedPassword =
// strongPasswordEncryptor.encryptPassword(userDto.getPassword());

// final UserDto user = new UserDto();
// user.setUsername(userDto.getUsername());
// user.setEmail(userDto.getEmail());
// user.setPassword(encryptedPassword);

// final UserResponse<UserDto> userResponse = new UserResponse<>();
// userResponse.setStatus("ok");
// userResponse.setMessage("success created account");
// userResponse.setResult(userRepository.save(user));

// return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
// }

// public ResponseEntity<UserResponse<UserDto>> loginUser(@Valid LoginRequest
// loginDTO) {
// StrongPasswordEncryptor strongPasswordEncryptor =
// context.getBean("strongPasswordEncryptor",
// StrongPasswordEncryptor.class);

// Optional<UserDto> userResult =
// userRepository.findByEmail(loginDTO.getEmail());
// if (userResult.isPresent()) {
// UserDto user = userResult.get();

// // Jika password sama
// if (strongPasswordEncryptor.checkPassword(loginDTO.getPassword(),
// user.getPassword())) {
// final UserResponse<UserDto> userResponse = new UserResponse<>();
// userResponse.setStatus("ok");
// userResponse.setMessage("success");
// userResponse.setResult(user);

// return ResponseEntity.status(HttpStatus.OK).body(userResponse);
// } else {
// final UserResponse<UserDto> userResponse = new UserResponse<>();
// userResponse.setStatus("error");
// userResponse.setMessage("password is not match to our server");
// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
// }

// } else {
// final UserResponse<UserDto> userResponse = new UserResponse<>();
// userResponse.setStatus("error");
// userResponse.setMessage("email is not found");

// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
// }
// }
// }
