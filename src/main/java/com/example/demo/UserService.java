package com.example.demo;

import java.util.List;

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
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password)) // Replace with encoded password check if using encryption
                .orElse(false);
    }

    public Users addUser(Users user) {
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("User already exists with ID: " + user.getId());
        }
        return userRepository.save(user);
    }

    public void deleteUsers(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public Users updateProduct(Long id, Users updatedUser) {
    	Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }
}