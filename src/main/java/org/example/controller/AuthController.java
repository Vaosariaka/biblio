package org.example.controller;

import org.example.entity.Personne;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private LoginService loginService;

    // Default route: redirect to /home if logged in, else / (index.jsp)
    @GetMapping("/")
    public String defaultRoute(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "index"; // Maps to index.jsp
    }

    // Display login form (same as default route for unauthenticated users)
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home"; // Redirect logged-in users to home
        }
        return "index"; // Maps to index.jsp
    }

    // Handle login submission
    @PostMapping("/auth/login")
    public String login(@RequestParam("login") String login, @RequestParam("mot_de_passe") String motDePasse, Model model, HttpSession session) {
        Optional<Personne> personne = loginService.login(login, motDePasse);
        if (personne.isPresent()) {
            session.setAttribute("user", personne.get()); // Store user in session
            return "redirect:/home"; // Redirect to home.jsp
        } else {
            model.addAttribute("error", "Login ou mot de passe incorrect");
            return "index"; // Return to index.jsp with error
        }
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/login"; // Redirect to login page
    }

    // Display home page (authenticated users only)
    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirect unauthenticated users to login
        }
        return "home"; // Maps to home.jsp
    }
}