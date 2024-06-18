package com.capestone.learning.controller;

import com.capestone.learning.model.User;
import com.capestone.learning.service.LoginService;
import com.capestone.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;

@Controller
public class Contoller {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        return "signIn";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
    @GetMapping("/courses")
    public String courses(Model model) {
        return "courses";
    }


    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody User user) {
        return new ResponseEntity<>(loginService.login(user), HttpStatus.OK);
    }

}
