package com.salah.ask.security.jwt;

import com.salah.ask.security.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    to intercept each request and examine the header for jwt
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(UserDetailsServiceImp userDetailsServiceImp,
                            JwtUtil jwtUtil) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String userName = null, jwt = null;
        if ((authorizationHeader != null) && (authorizationHeader.startsWith("Bearer "))) {
            jwt = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(jwt);
        }
        if (userName != null) {
            UserDetails userDetails = this.userDetailsServiceImp.loadUserByUsername(userName);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
