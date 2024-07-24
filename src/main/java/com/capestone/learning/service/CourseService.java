package com.capestone.learning.service;

import com.capestone.learning.entity.Course;
import com.capestone.learning.entity.Registration;
import com.capestone.learning.entity.User;
import com.capestone.learning.repository.CourseRepository;
import com.capestone.learning.repository.RegistrationRepository;
import com.capestone.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Course> getAvailableCoursesForLoggedInUser(String username) {
        // Retrieve the logged-in user from the repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Determine course availability based on user attributes
        boolean isAdmin = user.getRoles().contains("ROLE_ADMIN");

        // Retrieve available courses based on user attributes
        List<Course> availableCourses;
        List<Registration> registrations;
        if (isAdmin) {
            // Admin sees all courses
            availableCourses = courseRepository.findAll();
        } else {
            // Regular user sees only available courses
            //

            registrations = registrationRepository.findByUsername(username);
            if(registrations != null && registrations.size() > 0) {
                List<String> courses = registrations.stream().map(x -> x.getCourseId().toString()).collect(Collectors.toCollection(ArrayList<String>::new));
                return courseRepository.findAllById(courses);
            }else{
                availableCourses = courseRepository.findAll();
            }

        }

        return availableCourses;
    }
}
