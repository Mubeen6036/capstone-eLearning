package com.capestone.learning.repository;

import com.capestone.learning.entity.UserVideo;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserVideoRepository extends CassandraRepository<UserVideo, String> {
}
