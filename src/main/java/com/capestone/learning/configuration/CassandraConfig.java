package com.capestone.learning.configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class CassandraConfig {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${astra.client-secret}")
    public String clientSecret;
    @Value("${astra.client-id}")
    public String clientId;
    @Bean
    public CqlSession session() {
        try {
            Resource resource = resourceLoader.getResource("classpath:/secure-connect-capstone-learning.zip");
            return CqlSession.builder()
                    .withCloudSecureConnectBundle(Paths.get(resource.getURI()))
                    .withAuthCredentials(clientId, clientSecret)
                    .withKeyspace("capstone_learning")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
