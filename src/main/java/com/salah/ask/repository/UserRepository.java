package com.salah.ask.repository;

import com.salah.ask.model.user.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
