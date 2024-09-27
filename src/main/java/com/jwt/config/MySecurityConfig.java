package com.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Customize the security configuration as needed
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated() // Customize as per your authorization needs
            )
            .formLogin() // Enable form login, you can customize it if needed
            .and()
            .csrf().disable(); // Disable CSRF for simplicity, but it's recommended to enable it in production
        
        return http.build();
    }

    // For configuring AuthenticationManager, if needed
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
