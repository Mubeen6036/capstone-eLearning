package com.capestone.learning.repository;

import com.capestone.learning.entity.Course;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends CassandraRepository<Course, String> {
    List<Course> findByAvailable(boolean available);
    Course findByCourseId(UUID courseId);

    @Query("UPDATE capstone_learning.courses SET available = True where courseId = :courseId")
    void updateUserVideo(UUID courseId);

}
