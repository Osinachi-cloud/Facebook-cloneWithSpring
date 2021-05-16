package com.example.facebookweek7task.controller;

import com.example.facebookweek7task.dto.LogInDTO;
import com.example.facebookweek7task.dto.ResponseDTO;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    UserService userService;


    /**
     * Method to get the registration page in the frontend
     * @param model
     * @return the register page
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    /**
     * Method to save the details of the user to the database
     * @param user the user
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        ResponseDTO response = userService.addUser(user);

        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/";
        }
        model.addAttribute("message", response.getMessage());
        return "register";

    }

    /**
     * Method to get the index page
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getLogInPage(Model model) {
        model.addAttribute("user", new LogInDTO());
        return "index";
    }


    /**
     * Method to log in a user
     * @param user
     * @param model
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String logInUser(User user, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseDTO response = userService.logInUser(user);
        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            redirectAttributes.addFlashAttribute("user", response.getData());
            session.setAttribute("logUser", response.getData());
            return "redirect:/home";
        }
        model.addAttribute("message", response.getMessage());
        return "index";

    }

    /**
     * Method to get the logout page
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping("/logout")
    public String logUserOut(Model model, HttpSession httpSession) {

        if (httpSession != null) {
            httpSession.invalidate();
        }

        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "redirect:/";

    }


}
