package com.example.mentorseeker.controller;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;
import com.example.mentorseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @ModelAttribute(name = "userlist")
    public List<User> userlist() {
        return userService.getAll();
    }

    @ModelAttribute(name = "user")
    public User user() {
        User user = new User();
        return user;
    }

    @GetMapping
    public String view() {
        return "users-list";
    }

    @GetMapping("/create_user")
    public String createNewGame() {
        return "create-user";
    }

    @GetMapping("/{user_id}")
    public String view(@PathVariable("user_id") Long user_id, Model model) {
        model.addAttribute("user", userService.getById(user_id));
        return "user-page";
    }

    @PostMapping("/create_user")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("activities") String activitiesString) {
        List<String> activities = Arrays.asList(activitiesString.split(","));
        user.setActivities(activities);
        userService.create(user);
        return "redirect:/users" ;
    }

    @GetMapping("/{user_id}/delete")
    public String delete(@PathVariable("user_id") Long user_id){
        userService.delete(userService.getById(user_id));
        return "redirect:/users";
    }
}
