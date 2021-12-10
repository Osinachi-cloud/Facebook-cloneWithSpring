package com.example.facebookweek7task.controller;

import com.example.facebookweek7task.dto.ResponseDTO;
import com.example.facebookweek7task.entity.Comment;
import com.example.facebookweek7task.entity.Post;
import com.example.facebookweek7task.entity.PostLikes;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.mapper.LikePosts;
import com.example.facebookweek7task.service.CommentService;
import com.example.facebookweek7task.service.LikeService;
import com.example.facebookweek7task.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    /**
     * Method to get the home page
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("logUser");
        if (user==null) return "redirect:/";
        model.addAttribute("post", new Post());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("loggedUser", user);
        model.addAttribute("postLikes", new PostLikes());
        model.addAttribute("postDelete", new Post());

        List<LikePosts> listOfPosts = postService.getAllPost(user);

        model.addAttribute("listOfAllPosts", listOfPosts);
        return "home";
    }

    /**
     * Method to get the update post page
     * @param model
     * @param httpSession
     * @param postId
     * @return
     */
    @GetMapping("/updatepost")
    public String getUpdatePostPage(Model model, HttpSession httpSession, Long postId) {
        User user = (User) httpSession.getAttribute("logUser");
        if (user==null) return "redirect:/";

        Post post = postService.getPostById(postId);

        model.addAttribute("editpost", post);
        model.addAttribute("loggedUser", user);
        System.out.println(user);

        return "updatepost";
    }


    @PostMapping("/home_post")
    public String createPost(HttpSession httpSession, @ModelAttribute("post") Post post) {
        User user = (User) httpSession.getAttribute("logUser");
        postService.addPost(user, post);
            return "redirect:/home";
    }


    @PostMapping("/update")
    public String updatePost(HttpSession httpSession, Post post){
        Post newPost = postService.getPostById(post.getPostId());
        newPost.setBody(post.getBody());
        postService.updatePost(newPost);
        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = (User) httpSession.getAttribute("logUser");
        ResponseDTO response = new ResponseDTO();
        if (user == null) {
            return "redirect:/index";
        }
        Post post = postService.getPostById(id);
        commentService.deleteAllCommentsInPost(post);
        likeService.deleteAllLikesInPost(post);
        postService.deletePost(post);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/home";
    }

}
