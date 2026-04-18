package com.example.MusicApp.repository;

import com.example.MusicApp.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    public Account findByEmail(String email);
}
