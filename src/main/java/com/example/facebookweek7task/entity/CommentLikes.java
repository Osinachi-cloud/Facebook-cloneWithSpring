package com.example.facebookweek7task.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommentLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentLikeId;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private User user;
}
