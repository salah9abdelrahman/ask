package com.salah.ask.security;

import com.salah.ask.model.user.UserRoles;
import com.salah.ask.security.jwt.JwtRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtRequestFilter jwtRequestFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/dashboard",
            "/login/**", "/register/**", "/test/**"
    };


    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsServiceImp userDetailsServiceImp,
                          JwtRequestFilter jwtRequestFilter) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImp).passwordEncoder(this.passwordEncoder);
    }

    // securing web requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // enabling cors
                .cors().and()
                .csrf().disable()
                /**
                 * authorizing requests, ROLE: Restricted roles come first
                 */
                .authorizeRequests().antMatchers("/admin/**").hasRole(adminRole())
                // public urls
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/index")
                .and().exceptionHandling()
                .authenticationEntryPoint((req, rsp, e) -> {
                    log.info("Not authorized user");
                    rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


    private String regularRole() {
        return UserRoles.ROLE_REGULAR.name().substring(5);
    }

    private String adminRole() {
        return UserRoles.ROLE_ADMIN.name().substring(5);
    }

}
