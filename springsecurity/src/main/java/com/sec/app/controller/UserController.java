package com.sec.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sec.app.entity.Login;
import com.sec.app.entity.UserClass;
import com.sec.app.repository.UserRepository;
import com.sec.app.security.AuthConfig;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthConfig authConfig;
	
	@GetMapping(value="/getuser")
	public ResponseEntity<?> getAllUserController(){
		List<UserClass> users=userRepository.findAll();
		return ResponseEntity.ok().body(users);
	}
	@PostMapping(value="/saveuser")
	public ResponseEntity<?> saveUserController(@RequestBody UserClass user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserClass savedUser=userRepository.save(user);
		return ResponseEntity.ok().body(savedUser);
	}
	@PutMapping(value="/updateuser")
	public ResponseEntity<?> updateController(@RequestBody UserClass user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserClass updateUser=userRepository.save(user);
		return ResponseEntity.ok().body(updateUser);
	}
	@DeleteMapping(value="/deleteuser/{uuid}")
	public ResponseEntity<?> deleteUserByIdController(@PathVariable UUID uuid){
		Optional<UserClass> user=userRepository.findById(uuid);
		if(user.isPresent()) {
			userRepository.deleteById(uuid);
		}
		return ResponseEntity.ok().body(user.get());
	}
	@PostMapping(value="/login")
	public ResponseEntity<?> loginUserController(@RequestBody Login login){
		UserDetails userDetails=authConfig.loadUserByUsername(login.getEmail());
		Optional<UserClass> user=userRepository.findByEmail(login.getEmail());
		return ResponseEntity.ok().body(user.get());
	}
	

}
