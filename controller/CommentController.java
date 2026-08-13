package com.blog.controller;

import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentController(
            CommentRepository commentRepository,
            PostRepository postRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @PostMapping("/post/{postId}")
    public Comment addComment(
            @PathVariable Long postId,
            @RequestBody Comment comment) {

        Post post = postRepository.findById(postId).orElseThrow();

        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {

        commentRepository.deleteById(id);

        return "Comment Deleted";
    }
}
