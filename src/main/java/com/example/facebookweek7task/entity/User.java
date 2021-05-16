package com.example.facebookweek7task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surName;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String dateOfBirth;


    @Column(nullable = false)
    private String gender;

}
