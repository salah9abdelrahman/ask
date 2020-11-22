package com.salah.ask.security;

import com.salah.ask.model.user.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor

public class UserDetailsImp implements UserDetails {

    private String email;
    private String userFName;
    private String userLName;
    private String password;
    private boolean isActive;
    private List<GrantedAuthority> authorities;



    public UserDetailsImp(User user) {
        this.userFName = user.getFirstName();
        this.userLName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.isActive = user.isActive();
        this.authorities = new ArrayList<>();
         user.getRoles().forEach(role ->
                 this.authorities.add(new SimpleGrantedAuthority(role.getRole().name())));
//        this.authorities = user.get
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
