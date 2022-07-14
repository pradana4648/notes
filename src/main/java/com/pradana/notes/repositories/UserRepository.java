package com.pradana.notes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pradana.notes.pojo.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
