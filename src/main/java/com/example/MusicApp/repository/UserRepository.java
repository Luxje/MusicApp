package com.example.MusicApp.repository;

import com.example.MusicApp.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    public Users findByUserID(Integer userId);

    public Users findByEmail(String email);
}
