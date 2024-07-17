package com.capestone.learning.service;

import com.capestone.learning.entity.Course;
import com.capestone.learning.entity.Registration;
import com.capestone.learning.repository.CourseRepository;
import com.capestone.learning.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseRegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private CourseRepository courseRepository;

    // Course registration method
    public void registerCourse(UUID courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Registration registration = new Registration(username, courseId);
        registrationRepository.save(registration);
        Course course = courseRepository.findByCourseId(courseId);
        course.setAvailable(false);
        courseRepository.save(course);
    }

    public Boolean isRegistered(UUID courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Registration> registrations = registrationRepository.findByCourseIdAndUsername(courseId, username);
        if(registrations!=null && registrations.size()>0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
