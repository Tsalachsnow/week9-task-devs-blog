package com.devsblog1.controller;

import com.devsblog1.model.PostResponse;
import com.devsblog1.model.Post_Likes;
import com.devsblog1.model.Posts;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.Post_LikeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Post_LikeController {

    Post_LikeImpl post_likeImpl;
    PostServiceImpl postServiceImpl;

  @Autowired
    public Post_LikeController(Post_LikeImpl post_likeImpl, PostServiceImpl postServiceImpl) {
        this.post_likeImpl = post_likeImpl;
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("/getLikes/{postId}")
    public ResponseEntity<?> getLikesByPostId(@PathVariable Long postId, Post_Likes likes){
        Posts posts = postServiceImpl.findById(postId).get();
        Post_Likes postlks = new Post_Likes();
        if(posts != null){
            postlks.setPosts(posts);
            postlks.setLikes(1);
            post_likeImpl.create(postlks);
            PostResponse LikeResponse = new PostResponse();
            LikeResponse.setResponse("you have "+likes+ " now");
            return new ResponseEntity<>(LikeResponse, HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping("/decrementLikes/{postId}")
    public ResponseEntity<?> decrementLikes(@PathVariable("id") Long id, Post_Likes likes){
        Posts posts = postServiceImpl.findById(id).get();
        Post_Likes postlks = new Post_Likes();
        if(posts != null){
            postlks.setPosts(posts);
            postlks.setLikes(0);
            post_likeImpl.deleteById(postlks.getId());
            PostResponse LikeResponse = new PostResponse();
            LikeResponse.setResponse("you have "+likes+ " now");
            return new ResponseEntity<>(LikeResponse, HttpStatus.OK);
        }
        return null;
        }


}
