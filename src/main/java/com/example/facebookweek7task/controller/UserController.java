package com.example.facebookweek7task.controller;

import com.example.facebookweek7task.dto.LogInDTO;
import com.example.facebookweek7task.dto.ResponseDTO;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.repositories.UserRepository;
import com.example.facebookweek7task.service.UserService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.AttributedString;


@Controller
public class UserController {
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


//    @PostMapping("/register")
//    public String registerUser(User user, Model model) {
//        User gottenUser = userService.getUserByEmailAddress(user.getEmailAddress());
//
//        if (gottenUser != null) {
////            String message = "This email is already registered";
////            model.addAttribute("flash", message);
//            return "register";
//        }
//
//        userService.addUser(user);
//        return "login";
//    }

    @PostMapping("/register")
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        ResponseDTO response = userService.addUser(user);

        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/login";
        }

        model.addAttribute("message", response.getMessage());
        return "register";

    }

    @GetMapping("/login")
    public String getLogInPage(Model model) {
        model.addAttribute("user", new LogInDTO());
        return "login";
    }


    @PostMapping("/login_post")
    public String logInUser(User user, Model model, RedirectAttributes redirectAttributes) {
        ResponseDTO response = userService.logInUser(user);
        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("user", response.getData());
            return "redirect:/home";
        }
        model.addAttribute("message", response.getMessage());
        return "login";

    }


    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }


    @RequestMapping("/")
    public String add() {
        return "index";
    }
}
