package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repositories.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.validaEmail(username);
		
		if(user == null)
			throw new UsernameNotFoundException("User not found");
		return user;
	}

}
