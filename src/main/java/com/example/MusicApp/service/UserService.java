package com.example.MusicApp.service;

import com.example.MusicApp.entity.Artist;
import com.example.MusicApp.entity.SubscriptionPlan;
import com.example.MusicApp.entity.Users;
import com.example.MusicApp.repository.ArtistRepository;
import com.example.MusicApp.repository.SubscriptionPlanRepository;
import com.example.MusicApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private ArtistRepository artistRepository;


    public Boolean validateLogin(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
//            if (user.getPassword().equals(password)) {
                return true;
            }
//        }else {
//            return false;
//        }
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

    public Boolean validateRegister(String username, String email, String password, Integer subscriptionPlanId) {
        boolean validateEmail = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        if (!validateEmail) {
            System.out.println("sai dinh dang email");
            return false;
        }
            Users exitUser = userRepository.findByEmail(email);
        if (exitUser != null) {
            System.out.println("da ton tai");
            return false;
        }
                SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findBySubscriptionPlanID(subscriptionPlanId);
//                userRepository.save(new Users(null, username, email, password, subscriptionPlan));
                System.out.println("thanh cong");
                return true;
        }


        public Artist getArtistByUsername(String username) {
            Artist artist = artistRepository.findArtistByUser_Username(username);

            return artist;
        }

    }


