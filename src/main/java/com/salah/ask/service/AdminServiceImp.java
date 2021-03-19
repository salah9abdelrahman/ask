package com.salah.ask.service;

import com.salah.ask.model.user.User;
import com.salah.ask.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    private final JpaUserRepository userRepository;

    @Autowired
    public AdminServiceImp(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
