package com.salah.ask.service;

import com.salah.ask.model.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User testFirstCacheLevel(String newName);
}
