package com.salah.ask.controller;

import java.security.Principal;
import java.util.Optional;

import com.salah.ask.exception.custom.EntityNotFoundException;
import com.salah.ask.model.user.User;
import com.salah.ask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param principal: to know the logged user
     * @return
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email, Principal principal) {
        // the logged user
        Optional<User> user = userService.findByEmail(principal.getName());
        if (user.isPresent()) {
            return ResponseEntity.ok(userService.findByEmail(email));
        }

        throw new EntityNotFoundException(email + "not found");
    }

}
