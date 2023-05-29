package com.example.mentorseeker.controller;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;
import com.example.mentorseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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

    @ModelAttribute(name = "roles")
    public Role[] role(){
        return Role.values();
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
        User user = userService.getById(user_id);
        model.addAttribute("user", user);
        byte[] profilePictureBytes = user.getProfilePicture();

        if (profilePictureBytes != null) {
            String encodedImage = Base64.getEncoder().encodeToString(profilePictureBytes);
            model.addAttribute("encodedImage", encodedImage);
            model.addAttribute("byteImage", profilePictureBytes);
        }
        return "user-page";
    }

    @PostMapping("/create_user")
    public String createUser(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("city") String city,
                             @RequestParam("profilePicture") MultipartFile profilePicture,
                             @RequestParam("activities") String activitiesString,
                             @RequestParam("contacts") String contactsString,
                             @RequestParam("role") Role role) throws IOException{
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCity(city);
        user.setRole(role);
        byte[] profilePictureBytes = IOUtils.toByteArray(profilePicture.getInputStream());
        user.setProfilePicture(profilePictureBytes);
        List<String> activities = Arrays.asList(activitiesString.split(","));
        user.setActivities(activities);
        List<String> contacts = Arrays.asList(contactsString.split(","));
        user.setContacts(contacts);
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{user_id}/delete")
    public String delete(@PathVariable("user_id") Long user_id){
        userService.delete(userService.getById(user_id));
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping("/search")
    public String searchPost(@RequestParam("activity") String activity,
                             @RequestParam("city") String city,
                             @RequestParam("role") Role role,
                             Model model){
        if(city != "")
            model.addAttribute("userlist", userService.getSorted(activity, city, role));
        else
            model.addAttribute("userlist", userService.getSortedWOCity(activity, role));
        return "users-list-sorted";
    }
}
