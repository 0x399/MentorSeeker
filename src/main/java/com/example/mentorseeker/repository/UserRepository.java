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

    @Query("SELECT u.id FROM User u JOIN u.activities a WHERE a = :activity AND u.city = :city ORDER BY CASE WHEN a = :activity THEN 0 ELSE 1 END")
    List<Long> findIdsByActivityAndCity(@Param("activity") String activity, @Param("city") String city);

    @Query("SELECT u.id FROM User u JOIN u.activities a WHERE a = :activity AND u.city = :city AND u.role = :role ORDER BY CASE WHEN a = :activity THEN 0 ELSE 1 END")
    List<Long> findIdsByActivityCityAndRole(@Param("activity") String activity, @Param("city") String city, @Param("role") Role role);
}
