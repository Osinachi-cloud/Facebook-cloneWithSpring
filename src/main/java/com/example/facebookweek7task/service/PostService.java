package com.example.facebookweek7task.service;

import com.example.facebookweek7task.entity.Post;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.mapper.LikePosts;
import java.util.List;

public interface PostService {
    void addPost(User user, Post post);

    List<LikePosts> getAllPost(User user);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);
}
