package com.salah.ask.service;

import com.salah.ask.exception.custom.EntityNotFoundException;
import com.salah.ask.model.user.User;
import com.salah.ask.repository.JpaUserRepository;
import com.salah.ask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(@Qualifier("jpaUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user =  userRepository.findByEmail(email);
        user.orElseThrow(() -> new EntityNotFoundException("Not Found " + email));
        return user;
    }



}
