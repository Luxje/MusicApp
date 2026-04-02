package com.example.MusicApp.service;

import com.example.MusicApp.accountCredentials.LoginCredentials;
import com.example.MusicApp.accountCredentials.RegisterCredentials;
import com.example.MusicApp.entity.Account;
import com.example.MusicApp.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    final private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


    public Boolean validateLogin(String email, String password) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return false;
        }

        return encoder.matches(password, account.getPassword());

    }

    public boolean registerAccount(String email, String password, String username, String displayName, String profilePicture) {
        boolean emailFormat = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if (accountRepository.findByEmail(email) != null) {
            System.out.println("khong tao duoc tai khoan");
            return false;
        }
            String encodedPassword = encoder.encode(password);
            Account account = new Account(null ,username, email, encodedPassword, displayName, profilePicture, null , "ARTIST", true, 100, null);
            accountRepository.save(account);
        System.out.println("tao duoc tai khoan roi");
        return true;
    }


}
