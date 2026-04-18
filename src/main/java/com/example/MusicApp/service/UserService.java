package com.example.MusicApp.service;

import com.example.MusicApp.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final private AccountRepository accountRepository;

    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }




//    public String getTenByEmail(String email) {
//        Account account = accountRepository.findByEmail(email);
//        if (account != null) {
//            return account.getUsername();
//        }else {
//            return null;
//        }
//    }



    }


