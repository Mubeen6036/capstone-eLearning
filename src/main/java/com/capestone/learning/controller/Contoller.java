package com.capestone.learning.controller;

import com.capestone.learning.entity.Course;
import com.capestone.learning.entity.User;
import com.capestone.learning.jwt.JwtResponse;
import com.capestone.learning.requestout.CourseRegistrationResponse;
import com.capestone.learning.service.CourseRegistrationService;
import com.capestone.learning.service.CourseService;
import com.capestone.learning.service.LoginService;
import com.capestone.learning.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class Contoller {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CourseRegistrationService courseRegistrationService;
    @Autowired
    LoginService loginService;
    @Autowired
    CourseService courseService;

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/faculty/adam")
    public String adam(Model model) {
        return "adam";
    }

    @GetMapping("/faculty/amanda")
    public String amanda(Model model) {
        return "amanda";
    }
    @GetMapping("/faculty/david")
    public String david(Model model) {
        return "david";
    }
    @GetMapping("/faculty/emily")
    public String emily(Model model) {
        return "emily";
    }
    @GetMapping("/faculty/ethan")
    public String ethan(Model model) {
        return "ethan";
    }
    @GetMapping("/faculty/lucas")
    public String lucas(Model model) {
        return "lucas";
    }
    @GetMapping("/faculty/nathan")
    public String nathan(Model model) {
        return "nathan";
    }
    @GetMapping("/faculty/sarah")
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

    @GetMapping("/login")
    public String logIn(Model model) {
        return "signIn";
    }
    @GetMapping("/video")
    public String video(Model model) {
        return "video";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
    @GetMapping("/courses")
    public String showCourses(Model model, HttpServletRequest request) {
        return "courses";
    }

    @GetMapping("/courselist")
    @ResponseBody
    public ResponseEntity<List<Course>> showCourses() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Course> courses = courseService.getAvailableCoursesForLoggedInUser(username);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/faculty")
    public String faculty(Model model) {
        return "faculty";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user) {
        customUserDetailsService.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<JwtResponse> login(@RequestBody User user) {
        String token = loginService.login(user);
        if (token != null) {
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registerCourse")
    @ResponseBody
    public ResponseEntity<JwtResponse> registerCourse(@RequestBody Course course) {
        courseRegistrationService.registerCourse(course.getCourseId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/isRegistered")
    @ResponseBody
    public ResponseEntity<CourseRegistrationResponse> isRegistered(@RequestBody Course course) {
        CourseRegistrationResponse response = new CourseRegistrationResponse();
        response.setRegistered(courseRegistrationService.isRegistered(course.getCourseId()));
        return ResponseEntity.ok(response);
    }

}
