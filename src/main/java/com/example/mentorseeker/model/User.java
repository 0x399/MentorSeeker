package com.example.mentorseeker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "city", nullable = false)
    private String city;

    @ElementCollection
    @CollectionTable(name = "user_activities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "activity")
    private List<String> activities;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "user_associates",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "associate_id"))
    private List<User> associates;

    @ElementCollection
    @CollectionTable(name = "user_contacts", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "contact")
    private List<String> contacts;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "bytea")
    private byte[] profilePicture;
}
