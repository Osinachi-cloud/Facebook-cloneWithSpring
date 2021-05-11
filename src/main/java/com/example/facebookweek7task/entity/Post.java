package com.example.facebookweek7task.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> listOfComments = new ArrayList<>();


    @OneToMany
    private List<PostLikes> postLikes = new ArrayList<>();
}
