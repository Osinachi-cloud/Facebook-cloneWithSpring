package com.example.facebookweek7task.repositories;

import com.example.facebookweek7task.entity.Post;
import com.example.facebookweek7task.entity.PostLikes;
import com.example.facebookweek7task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<PostLikes, Long> {

    @Transactional
    void deletePostLikesByPostAndUser(Post post, User user);

    List<PostLikes> findAllByPost(Post post);

    void deleteAllByPost(Post post);

}
