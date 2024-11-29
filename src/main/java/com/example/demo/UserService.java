package com.example.demo;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // Implement methods to create and manage users if needed
	//private UserRepository userRepository;
	
	private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    
    public boolean authenticateUser(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//i have added this to encrypt password
        return userRepository.findByEmail(email)
                .map(user -> encoder.matches(password, user.getPassword())) // Replace with encoded password check if using encryption
                .orElse(false);
        // encoder.matches(password, user.getPassword()) //i have replaced : user.getPassword().equals(password) by to encrypt password
        //return encoder.matches(password, user.getPassword());
    }

    public Users addUser(Users user) {
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("User already exists with ID: " + user.getId());
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //i have added this to encrypt password
        user.setPassword(encoder.encode(user.getPassword()));//i have added this to encrypt password
        return userRepository.save(user);
    }
}