package com.pradana.notes.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradana.notes.dto.UserDto;
import com.pradana.notes.services.UserService;
import com.pradana.notes.templates.UserResponse;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> result = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errorMsg = err.getDefaultMessage();
            result.put(fieldName, errorMsg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}