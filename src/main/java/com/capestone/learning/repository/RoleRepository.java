package com.capestone.learning.repository;

import com.capestone.learning.entity.Role;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RoleRepository extends CassandraRepository<Role, String> {
}
