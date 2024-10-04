package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtResponse;
import com.jwt.model.jwtRequest;
import com.jwt.services.CustomerUserDetailService;

@RestController
public class jwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest JwtRequest) throws Exception {
	    
	    System.out.println("Received request to generate token for: " + JwtRequest.getUsername());
	    
	    try {
	        // Perform authentication
	        this.authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(JwtRequest.getUsername(), JwtRequest.getPassword())
	        );
	        
	    } catch (BadCredentialsException e) {
	        System.out.println("Invalid credentials provided");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        
	    } catch (UsernameNotFoundException e) {
	        System.out.println("Username not found: " + JwtRequest.getUsername());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        
	    } catch (Exception e) {
	        System.out.println("Unexpected error during authentication: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	    
	    // If authentication succeeds, generate the token
	    UserDetails userDetails = this.customerUserDetailService.loadUserByUsername(JwtRequest.getUsername());
	    String token = this.jwtUtil.generateToken(userDetails);
	    
	    System.out.println("Generated JWT token: " + token);
	    
	    return ResponseEntity.ok(new JwtResponse(token));
	}
}