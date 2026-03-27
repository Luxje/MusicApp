package com.example.MusicApp.service;

import com.example.MusicApp.entity.Account;
import com.example.MusicApp.entity.Artist;
import com.example.MusicApp.entity.SubscriptionPlan;
import com.example.MusicApp.entity.Users;
import com.example.MusicApp.repository.AccountRepository;
import com.example.MusicApp.repository.ArtistRepository;
import com.example.MusicApp.repository.SubscriptionPlanRepository;
import com.example.MusicApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


