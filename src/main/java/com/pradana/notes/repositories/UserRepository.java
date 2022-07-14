package com.pradana.notes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pradana.notes.pojo.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
