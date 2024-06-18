package com.capestone.learning.service;

import com.capestone.learning.model.User;
import com.capestone.learning.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void addUser(User user){
        userDao.addUser(user);
    }
}
