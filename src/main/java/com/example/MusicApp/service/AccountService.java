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


    public Boolean validateLogin(LoginCredentials loginCredentials) {
        Account account = accountRepository.findByEmail(loginCredentials.getEmail());
        if (account == null) {
            return false;
        }

        return encoder.matches(loginCredentials.getPassword(), account.getPassword());

    }

    public boolean registerAccount(RegisterCredentials registerCredentials) {
        boolean emailFormat = registerCredentials.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        if (emailFormat) {
            return false;
        }
        if (accountRepository.findByEmail(registerCredentials.getEmail()) != null) {
            return false;
        }
            String encodedPassword = encoder.encode(registerCredentials.getPassword());
            Account account = new Account();
            account.setEmail(registerCredentials.getEmail());
            account.setPassword(encodedPassword);
            accountRepository.save(account);
            return true;
    }

}
