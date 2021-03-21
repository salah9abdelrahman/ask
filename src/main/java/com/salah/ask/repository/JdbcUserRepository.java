package com.salah.ask.repository;

import com.salah.ask.model.user.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

//@Repository
public class JdbcUserRepository implements UserRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        /*
         * by namedParameterJdbcTemplate
         */
        return namedParameterJdbcTemplate.queryForObject(
                "select * from \"user\" where email = :email",
                new MapSqlParameterSource("email", email),
                this::mapRowToUser
        );
    }

    private Optional<User> mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return Optional.of(new User(
                rs.getString("email")
        ));
    }


}
