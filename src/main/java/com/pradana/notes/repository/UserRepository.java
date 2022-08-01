package com.pradana.notes.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pradana.notes.dto.model.UserDto;

public interface UserRepository extends CrudRepository<UserDto, String> {
    Optional<UserDto> findByEmail(String email);
}
