package com.example.facebookweek7task.service;

import com.example.facebookweek7task.entity.Comment;
import com.example.facebookweek7task.entity.Post;

import java.util.List;

public interface CommentService {
//    void addComment(User user, Comment comment, Long postId);

//    List<Comment> getAllComment();

    void updateComment(Comment editedComment);

//    void deleteComment(Comment comment);

    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    List<Comment> findCommentByPost(Post post);

    void deleteComment(Long commentId);

    void deleteAllCommentsInPost(Post post);
}
