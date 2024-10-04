package com.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    // Implement the loadUserByUsername method to fetch user details
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    System.out.println("Attempting to load user: " + username);
	    
	    // Use a pre-encoded password here
	    String encodedPassword = "$2a$10$.8lYzMSUgxkD2IkxQa.Peu1jAafgOnOZPhm2KFdE.q8ShN0K7./KC"; // BCrypt encoded password for "shivam123"

	    if (username.equals("Shivam")) {
	        System.out.println("User found, returning user details");
	        return new User("Shivam", encodedPassword, new ArrayList<>());
	    } else {
	        System.out.println("User not found: " + username);
	        throw new UsernameNotFoundException("User not found");
	    }
	}



}
