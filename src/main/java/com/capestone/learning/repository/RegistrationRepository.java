package com.capestone.learning.repository;

import com.capestone.learning.entity.Registration;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends CassandraRepository<Registration, UUID> {
    public List<Registration> findByCourseIdAndUsername(UUID courseId, String username);
    public List<Registration> findByUsername(String username);
}
