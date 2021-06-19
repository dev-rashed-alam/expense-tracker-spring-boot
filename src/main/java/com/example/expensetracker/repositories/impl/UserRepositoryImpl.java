package com.example.expensetracker.repositories.impl;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;
import com.example.expensetracker.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final String SQL_CREATE = "insert into et_users(first_name,last_name,email,password) values (?,?,?,?)";

    public static final String SQL_COUNT_BY_EMAIL = "select count(*) from et_users where email = ?";

    public static final String SQL_FIND_BY_ID = "select * from et_users where user_id = ?";

    public static final String SQL_FIND_BY_EMAIL_AND_PASSWORD = "select * from et_users where email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        try {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).intValue();
        } catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL_AND_PASSWORD, userRowMapper, email);
            if (!BCrypt.checkpw(password, user.getPassword()))
                throw new EtAuthException("Invalid credentials");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid credentials");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, email);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, userId);
    }

    public RowMapper<User> userRowMapper = ((res, rowNum) ->
            new User(
                    res.getInt("user_id"),
                    res.getString("first_name"),
                    res.getString("last_name"),
                    res.getString("email"),
                    res.getString("password")
            )
    );

}
