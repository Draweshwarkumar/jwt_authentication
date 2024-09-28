package com.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    // Implement the loadUserByUsername method to fetch user details
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      if(username.equals("Shivam")) {
    	  return new User("Shivam","shivam123",new ArrayList<>());
      }
      else {
    	  throw new UsernameNotFoundException("User not found");
      }
       
    }
}
