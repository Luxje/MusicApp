package com.example.MusicApp.repository;

import com.example.MusicApp.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    public Users findByUserID(Integer userId);

    public Users findByEmail(String email);
}
