package com.capestone.learning.repository;

import com.capestone.learning.entity.Video;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VideoRepository extends CassandraRepository<Video, String> {
    List<Video> findAllByCourseUuid(UUID courseId);
}
