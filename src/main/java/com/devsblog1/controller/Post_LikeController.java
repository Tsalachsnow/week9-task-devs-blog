package com.devsblog1.controller;

import com.devsblog1.model.Response;
import com.devsblog1.model.Post_Likes;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.Post_LikeImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
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
    UserServiceImpl userServiceImpl;

    @Autowired
    public Post_LikeController(Post_LikeImpl post_likeImpl, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.post_likeImpl = post_likeImpl;
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/getLikes/{postId}/{id}")
    public ResponseEntity<?> getLikesByPostId(@PathVariable Long postId, Post_Likes likes,@PathVariable ("id")Long id){
      var users = userServiceImpl.findById(id);
        Posts posts = postServiceImpl.findById(postId).get();
        Post_Likes postlks = new Post_Likes();
        if(posts != null){
            if(postlks.getLikes()==1){
                postlks.setLikes(0);
//            post_likeImpl.deleteById(postId);
//            post_likeImpl.deleteById(id);
//                post_likeImpl.create(postlks);
            Response LikeResponse = new Response();
            LikeResponse.setResponse("your post was unliked");
            new ResponseEntity<>(LikeResponse, HttpStatus.OK);
            }else{
                if(postlks.getLikes()==0)
                    postlks.setLikes(1);

            postlks.setPosts(posts);
            postlks.setUsers(users.get());
            post_likeImpl.create(postlks);
            Response LikeResponse1 = new Response();
            LikeResponse1.setResponse("you have "+postlks.getLikes()+" likes now");

            return new ResponseEntity<>(LikeResponse1, HttpStatus.OK);}
//                throw new IllegalStateException("you cannot like more than once");
            }
//            postlks.setPosts(posts);

        return null;
        }



    @DeleteMapping("/decrementLikes/{postId}")
    public ResponseEntity<?> decrementLikes(@PathVariable("id") Long id, Post_Likes likes,@PathVariable("id") Long uId){
        var users = userServiceImpl.findById(uId);
        Posts posts = postServiceImpl.findById(id).get();
        Post_Likes postlks = new Post_Likes();
        if(posts != null&&users!=null){
            if(users.isPresent())
//            postlks.setPosts(posts);
            postlks.setLikes(0);
            post_likeImpl.deleteById(postlks.getId());
            Response LikeResponse = new Response();
            LikeResponse.setResponse("you have "+postlks.getLikes()+" likes now");
            return new ResponseEntity<>(LikeResponse, HttpStatus.OK);
        }
        return null;
        }


}
