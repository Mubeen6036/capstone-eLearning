package com.capestone.learning.repository;

import com.capestone.learning.entity.Video;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface VideoRepository extends CassandraRepository<Video, String> {
}
