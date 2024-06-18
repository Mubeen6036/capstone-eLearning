package com.capestone.learning.repository;

import com.capestone.learning.model.User;
import com.datastax.astra.sdk.AstraClient;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import io.stargate.sdk.core.domain.RowResultPage;
import io.stargate.sdk.rest.StargateRestApiClient;
import io.stargate.sdk.rest.TableClient;
import io.stargate.sdk.rest.domain.SearchTableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao{
    @Autowired
    CqlSession cqlSession;
        public void addUser(User user) {
        try{
            PreparedStatement preparedStatement = cqlSession.prepare(
                    "INSERT INTO users (first_name, last_name, gmail, password, mobile_num) VALUES (?, ?, ?, ?, ?)");

            cqlSession.execute(preparedStatement.bind(user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getPassword(), user.getPhoneNumber()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(User user) {
        try{
            PreparedStatement preparedStatement = cqlSession.prepare(
                    "select password from users where gmail = ?");

            ResultSet resultSet = cqlSession.execute(preparedStatement.bind(user.getEmail()));
            User match = new User();
            for(Row row: resultSet){
                match.setPassword(row.getString("password"));
            }
            return match;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
