package com.example.facebookweek7task.serviceimplementation;

import com.example.facebookweek7task.dto.ResponseDTO;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.repositories.UserRepository;
import com.example.facebookweek7task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Method to register a user and save the details to the database
     * @param user the user to be registered
     * @return the response
     */
    public ResponseDTO addUser(User user) {
        Optional<User> userDb = userRepository.getUserByEmailAddress(user.getEmailAddress());
        ResponseDTO response = new ResponseDTO();

        try{
            if (userDb.isPresent()) {
                throw new Exception("This email is already registered");
            }
            User savedUser = userRepository.save(user);
            response.setData(savedUser);
            response.setMessage("Registration successful");
            response.setStatus(true);
            return response;

        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(false);
            return response;

    }
}

    /**
     * Method to log in a user
     * @param user the user to be logged in
     * @return the response
     */

    public ResponseDTO logInUser(User user) {
        Optional<User> userDb = userRepository.getUserByEmailAddressAndPassword
                (user.getEmailAddress(), user.getPassword());

        ResponseDTO response = new ResponseDTO();

        if (userDb.isPresent()) {
            response.setStatus(true);
            response.setData(userDb.get());
            response.setMessage("LogIn successful");
            return response;
        }
        response.setMessage("Invalid email or password");
        return response;

    }

}
