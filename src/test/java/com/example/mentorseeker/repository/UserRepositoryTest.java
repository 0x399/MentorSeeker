package com.example.mentorseeker.repository;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByCityTest(){
        List<User> users = userRepository.findByCity("London");
        for (User user : users)
            userRepository.delete(user);
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bobinson");
        user.setCity("London");
        user.setRole(Role.ROLE_MENTOR);
        userRepository.save(user);
        assertEquals(userRepository.findByCity("London").size(), 1);
        assertTrue(userRepository.findByCity("London").get(0).getRole().equals(Role.ROLE_MENTOR));
        userRepository.delete(user);
    }

    @Test
    void findByRole() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bobinson");
        user.setCity("London");
        user.setRole(Role.ROLE_MENTOR);
        userRepository.save(user);
        assertEquals(userRepository.findByCity("London").size(), 1);
        assertTrue(userRepository.findByCity("London").get(0).getRole().equals(Role.ROLE_MENTOR));
        userRepository.delete(user);
    }

    @Test
    void findByActivity() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bobinson");
        user.setCity("London");
        user.setRole(Role.ROLE_MENTOR);
        List<String> activities = new ArrayList<>();
        activities.add("Skiing");
        activities.add("Skating");
        user.setActivities(activities);
        userRepository.save(user);
        assertTrue(userRepository.findByActivity("Skiing").size() == 1);
        userRepository.delete(user);
    }

    @Test
    void findIdsByActivityAndRole() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bobinson");
        user.setCity("London");
        user.setRole(Role.ROLE_MENTOR);
        List<String> activities = new ArrayList<>();
        activities.add("Skiing");
        activities.add("Skating");
        user.setActivities(activities);
        userRepository.save(user);
        assertTrue(userRepository.findIdsByActivityAndRole("Skiing", Role.ROLE_MENTOR).size()==1);
        userRepository.delete(user);
    }

    @Test
    void findIdsByActivityCityAndRole() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bobinson");
        user.setCity("London");
        user.setRole(Role.ROLE_MENTOR);
        List<String> activities = new ArrayList<>();
        activities.add("Skiing");
        activities.add("Skating");
        user.setActivities(activities);
        userRepository.save(user);
        assertTrue(userRepository.findIdsByActivityCityAndRole("Skiing", "London", Role.ROLE_MENTOR).size() == 1);
        userRepository.delete(user);
    }
}