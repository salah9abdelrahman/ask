package com.salah.ask.service;

import com.salah.ask.dto.AuthResponse;
import com.salah.ask.dto.LoginRequest;
import com.salah.ask.security.UserDetailsServiceImp;
import com.salah.ask.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtUtil jwtUtil;

    @Autowired
    public LoginServiceImp(AuthenticationManager authenticationManager, UserDetailsServiceImp userDetailsServiceImp, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        try {
 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Not Authenticated");
        }
        // Create JWT token
        final UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(loginRequest.getEmail());
        System.out.println("userDetails");
        System.out.println(userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthResponse(jwt);
    }
}
