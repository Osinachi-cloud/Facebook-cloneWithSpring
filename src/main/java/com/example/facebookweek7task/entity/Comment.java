package com.example.facebookweek7task.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CommentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @OneToMany
    private List<CommentLikes> listOfCommentLikes = new ArrayList<>();
}
