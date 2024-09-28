package com.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    // Implement the loadUserByUsername method to fetch user details
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from database and return UserDetails
        // Example: return new User(...);
        throw new UsernameNotFoundException("User not found");
    }
}
