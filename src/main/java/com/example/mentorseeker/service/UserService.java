package com.example.mentorseeker.service;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;
import com.example.mentorseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<User> getByRole(Role role){
        return userRepository.findByRole(role);
    }

    public List<User> getByActivity(String activity){
        return userRepository.findByActivity(activity);
    }

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getByCity(String city){
        return userRepository.findByCity(city);
    }

    public List<User> getSorted(String activity, String city, Role role){
        List<Long> ids = userRepository.findIdsByActivityCityAndRole(activity, city, role);
        List<User> result = new ArrayList<>();
        for(Long id : ids)
            result.add(getById(id));
        return result;
    }

    public List<User> getSortedWOCity(String activity, Role role){
        List<Long> ids = userRepository.findIdsByActivityAndRole(activity, role);
        List<User> result = new ArrayList<>();
        for(Long id : ids)
            result.add(getById(id));
        return result;
    }

    public User update(User user){
        User userFromDB = userRepository.findById(user.getId()).orElseThrow();
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setAssociates(user.getAssociates());
        userFromDB.setActivities(user.getActivities());
        userFromDB.setContacts(user.getContacts());
        userFromDB.setCity(user.getCity());
        userRepository.save(userFromDB);
        return userFromDB;
    }

    public void delete(User user){
        userRepository.delete(user);
    }
}
