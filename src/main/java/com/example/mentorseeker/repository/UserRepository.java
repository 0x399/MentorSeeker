package com.example.mentorseeker.repository;

import com.example.mentorseeker.model.Role;
import com.example.mentorseeker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCity(String city);

    List<User> findByRole(Role role);


    @Query("SELECT u FROM User u JOIN u.activities a WHERE a = :activity ORDER BY CASE WHEN a = :activity THEN 0 ELSE 1 END")
    List<User> findByActivity(@Param("activity") String activity);

}
