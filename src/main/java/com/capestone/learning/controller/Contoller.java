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

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/adam")
    public String adam(Model model) {
        return "adam";
    }

    @GetMapping("/amanda")
    public String amanda(Model model) {
        return "amanda";
    }
    @GetMapping("/david")
    public String david(Model model) {
        return "david";
    }
    @GetMapping("/emily")
    public String emily(Model model) {
        return "emily";
    }
    @GetMapping("/ethan")
    public String ethan(Model model) {
        return "ethan";
    }
    @GetMapping("/lucas")
    public String lucas(Model model) {
        return "lucas";
    }
    @GetMapping("/nathan")
    public String nathan(Model model) {
        return "nathan";
    }
    @GetMapping("/sarah")
    public String sarah(Model model) {
        return "sarah";
    }
    @GetMapping("/index")
    public String index(Model model) {
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

    @GetMapping("/faculty")
    public String faculty(Model model) {
        return "faculty";
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
