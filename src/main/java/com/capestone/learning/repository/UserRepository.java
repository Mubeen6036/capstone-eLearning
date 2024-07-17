package com.capestone.learning.repository;

import com.capestone.learning.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CassandraRepository<User, String> {
    Optional<User> findByUsername(String username);
}
