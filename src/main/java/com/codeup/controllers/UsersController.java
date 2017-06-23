package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by fer on 6/23/17.
 */
@Controller
public class UsersController {


    @Autowired
    UsersRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/users/register")
    public String saveUser(@ModelAttribute User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDao.save(user);

        return "redirect:/posts";

    }



}
