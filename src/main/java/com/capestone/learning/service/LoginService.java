package com.capestone.learning.service;

import com.capestone.learning.model.User;
import com.capestone.learning.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserDao userDao;

    public String login(User user) {
        User match = userDao.getUser(user);
        if(user.getPassword().equals(match.getPassword())){
            return "Login Successful";
        }else{
            return "Login Failed";
        }
    }
}
