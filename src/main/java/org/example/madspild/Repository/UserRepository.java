package org.example.madspild.Repository;

import org.example.madspild.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    //adds user to database, returns true if successful
    public boolean addUser(User p) {
        String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
        int rowsAffected = template.update(sql, p.getId(), p.getUsername(), p.getPassword());
        return rowsAffected > 0;
    }

    //returns a User object if username exists (check if user exists before running)
    public User findUserByUserName(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User p = template.queryForObject(sql, rowMapper, username);
        return p;
    }

    //returns true if user exists in db
    public boolean doesUserExist(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = template.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }


    //returns true if user with @param username and @param password exists in db
    public boolean validateUserNameWithPassword(String username, String password){
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        Integer count = template.queryForObject(sql, Integer.class, username, password);
        return count != null && count > 0;
    }

}
