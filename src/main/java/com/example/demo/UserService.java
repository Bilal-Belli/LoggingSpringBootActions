package com.example.demo;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	public List<Users> getAllUsers() {
		logger.info("Operation=READ, Entity=Users, Action=FetchAll");
        return userRepository.findAll();
    }
	
	public Users getUserByEmail(String email) {
	    return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	}
	
    public Users getUserById(Long id) {
    	logger.info("Operation=READ, Entity=Users, Action=FetchById, UserId={}", id);
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    
    public boolean authenticateUser(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //i have added this to encrypt password
        return userRepository.findByEmail(email)
                .map(user -> encoder.matches(password, user.getPassword())) // Replace with encoded password check if using encryption
                .orElse(false);
    }

    public void addUser(Users user) {
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("User already exists with ID: " + user.getId());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //i have added this to encrypt password
        user.setPassword(encoder.encode(user.getPassword()));        //i have added this to encrypt password
        logger.info("Operation=WRITE, Entity=Users, Action=Add, UserId={}", user.getId());
        userRepository.save(user);
    }
}