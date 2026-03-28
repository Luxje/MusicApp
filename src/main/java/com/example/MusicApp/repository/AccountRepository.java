package com.example.MusicApp.repository;

import com.example.MusicApp.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    public Account findByEmail(String email);
}
