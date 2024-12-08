package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new Users());
        return "login_page";
    }
    
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new Users());
        return "signup_page";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Users user, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            boolean isAuthenticated = userService.authenticateUser(user.getEmail(), user.getPassword());
            if (isAuthenticated) {
                Users authenticatedUser = userService.getUserByEmail(user.getEmail());
                session.setAttribute("userId", authenticatedUser.getId());
                redirectAttributes.addFlashAttribute("message", "User connected successfully!");
                return "redirect:/products";
            } else {
                redirectAttributes.addFlashAttribute("message", "Invalid email or password.");
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }
    
    @PostMapping("/signup")
    public String processSignup(Users user, RedirectAttributes redirectAttributes) {
        try {
            // Save the user to the database
            userService.addUser(user);
            redirectAttributes.addFlashAttribute("message", "Account created successfully!");
            return "redirect:/users/login"; // Redirect to login after successful signup
        } catch (Exception e) {
        	//System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/users/signup";
        }
    }
}