package com.example.mentorseeker.service;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;

import java.util.List;

public interface UserServiceInterface {
    public User create(User user);

    public User update(User user);

    public void delete(User user);

    public List<User> getAll();

    public List<User> getByRole(Role role);
}
