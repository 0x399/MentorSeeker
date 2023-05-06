package com.example.mentorseeker.model;


public enum Role {
    ROLE_STUDENT("Student"), ROLE_MENTOR("Mentor");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
