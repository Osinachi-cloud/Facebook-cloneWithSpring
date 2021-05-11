package com.example.facebookweek7task.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="likes")
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postLikeId;


    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
