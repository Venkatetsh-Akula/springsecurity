package com.sec.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.app.entity.UserClass;
import com.sec.app.repository.UserRepository;

@Service
public class AuthConfig implements UserDetailsService {  // UserDetailsService predefine interface in spring security
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserClass user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));
		return new UserConfig(user);
	}

}
