package com.salah.ask.service;

import com.salah.ask.exception.custom.EntityNotFoundException;
import com.salah.ask.model.user.User;
import com.salah.ask.repository.JpaUserRepository;
import com.salah.ask.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class UserServiceImp implements UserService {
    private final JpaUserRepository userRepository;

    @Autowired
    public UserServiceImp(@Qualifier("jpaUserRepository") JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user =  userRepository.findByEmail(email);
        user.orElseThrow(() -> new EntityNotFoundException("Not Found " + email));
        return user;
    }

    @Override
    public User testFirstCacheLevel(String newName){

        Optional<User> userOptional = userRepository.findById(1L);


        userOptional.get().setFirstName(newName);

        userRepository.save(userOptional.get());

        //Didn't hit the database
        Optional<User> userAfterSaveOptional = userRepository.findById(1L);

        log.info("user name after updating it with value: " + newName + " has value:  " +
                userAfterSaveOptional.get().getFirstName());

        return userAfterSaveOptional.get();
    }

}
