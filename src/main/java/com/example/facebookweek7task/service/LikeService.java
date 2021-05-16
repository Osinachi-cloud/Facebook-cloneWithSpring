package com.example.facebookweek7task.service;

import com.example.facebookweek7task.entity.Post;
import com.example.facebookweek7task.entity.User;


public interface LikeService {
    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);

}
