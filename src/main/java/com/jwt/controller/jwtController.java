package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
	
	
	@RequestMapping(value="/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest JwtRequest ) throws Exception{
		
		System.out.println(JwtRequest);
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(JwtRequest.getUsername(),JwtRequest.getPassword()));
			
			
		} catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = this.customerUserDetailService.loadUserByUsername(JwtRequest.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("Jwt"+token);
		
//		{"token":"value"}
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}

}
