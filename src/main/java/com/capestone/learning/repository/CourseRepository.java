package com.capestone.learning.repository;

import com.capestone.learning.entity.Course;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends CassandraRepository<Course, String> {
    List<Course> findByAvailable(boolean available);
    Course findByCourseId(UUID courseId);


}
