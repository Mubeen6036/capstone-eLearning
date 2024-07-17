package com.capestone.learning.entity;

import com.capestone.learning.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table("registrations")
public class Registration implements Serializable {

        @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String username;

        @PrimaryKeyColumn(name = "courseid", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private UUID courseId;

        // Constructors, getters, and setters
        public Registration() {
        }

        public Registration(String username, UUID courseId) {
            this.username = username;
            this.courseId = courseId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public UUID getCourseId() {
            return courseId;
        }

        public void setCourseId(UUID courseId) {
            this.courseId = courseId;
        }
}
