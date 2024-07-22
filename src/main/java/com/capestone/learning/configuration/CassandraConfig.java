package com.capestone.learning.configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;

@Configuration
//@EnableCassandraRepositories(basePackages = "com.capestone.learning.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${astra.client-secret}")
    public String clientSecret;
    @Value("${astra.client-id}")
    public String clientId;

    @Value("${astra.keyspace}")
    public String keyspace;


        @Bean
        @Primary
        public CqlSession session() {
            try {
                Resource resource = resourceLoader.getResource("classpath:/secure-connect-capstone-learning.zip");
                //Resource resource = resourceLoader.getResource("classpath:/db-connect.zip");
                return CqlSession.builder()
                        .withCloudSecureConnectBundle(Paths.get(resource.getURI()))
                        .withAuthCredentials(clientId, clientSecret)
                        .withKeyspace(getKeyspaceName())
                        .withConfigLoader(
                                DriverConfigLoader.programmaticBuilder()
                                        .withDuration(DefaultDriverOption.CONNECTION_INIT_QUERY_TIMEOUT, Duration.ofSeconds(20))
                                        .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(20))
                                        .build())
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    @Override
    protected SessionBuilderConfigurer getSessionBuilderConfigurer() {
        Resource resource = resourceLoader.getResource("classpath:/secure-connect-capstone-learning.zip");
        //Resource resource = resourceLoader.getResource("classpath:/db-connect.zip");
        return builder -> {
            try {
                return builder
                        .withCloudSecureConnectBundle(Paths.get(resource.getURI()))
                        .withAuthCredentials(clientId, clientSecret);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

//    @Bean
//    public CassandraMappingContext cassandraMapping() {
//        return new CassandraMappingContext();
//    }
//
//    @Bean
//    public MappingCassandraConverter cassandraConverter() {
//        return new MappingCassandraConverter(cassandraMapping());
//    }
//
//    @Bean
//    public CassandraTemplate cassandraTemplate() {
//        return new CassandraTemplate(cassandraSession().getObject(), cassandraConverter());
//    }

    //@Bean
//    public CassandraMappingContext cassandraMappingContext() {
//        return new CassandraMappingContext();
//    }
//
//    @Bean
//    public MappingCassandraConverter cassandraConverter(CassandraMappingContext cassandraMappingContext) {
//        return new MappingCassandraConverter(cassandraMappingContext);
//    }
//
//    @Bean
//    public CassandraTemplate cassandraTemplate(CqlSession session, MappingCassandraConverter converter) {
//        return new CassandraTemplate(session, converter);
//    }


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }
//
}
