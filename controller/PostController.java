package com.blog.controller;

import com.blog.model.Post;
import com.blog.repository.PostRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {


private final PostRepository repo;


public PostController(PostRepository repo){
    this.repo=repo;
}


@GetMapping
public List<Post> getPosts(){
    return repo.findAll();
}


@PostMapping
public Post createPost(@RequestBody Post post){
    return repo.save(post);
}


@PutMapping("/{id}")
public Post updatePost(
@PathVariable Long id,
@RequestBody Post post){

    Post old=repo.findById(id).get();

    old.setTitle(post.getTitle());
    old.setContent(post.getContent());

    return repo.save(old);
}


@DeleteMapping("/{id}")
public String deletePost(@PathVariable Long id){

    repo.deleteById(id);

    return "Post Deleted";
}

}
