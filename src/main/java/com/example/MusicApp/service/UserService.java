package com.example.MusicApp.service;

import com.example.MusicApp.entity.SubscriptionPlan;
import com.example.MusicApp.entity.Users;
import com.example.MusicApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Boolean validateLogin(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }else {
            return false;
        }
        return null;
    }

    public String getTenByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getUsername();
        }else {
            return null;
        }
    }

    public Boolean validateRegister(String username, String email, String password, SubscriptionPlan subscriptionPlan) {
        Users user = new Users(null, username, email, password, subscriptionPlan );
        userRepository.save(user);
    }


}
