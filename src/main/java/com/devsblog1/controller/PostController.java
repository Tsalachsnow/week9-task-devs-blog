package com.devsblog1.controller;

import com.devsblog1.model.PostDto;
import com.devsblog1.model.Response;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    Users user1;

    PostServiceImpl postServiceImpl;
    UserServiceImpl userServiceImpl;
  @Autowired
    public PostController(PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/makes/{id}")
    public ResponseEntity<Response> makePost(@RequestBody PostDto posts, @PathVariable Long id){
       Users users1 =  userServiceImpl.findById(id).get();
       if(users1 != null){
          postServiceImpl.makePost(id,posts);
          Response response = new Response();
          response.setResponse("This text "+ posts.getTitle() + " this content " + posts.getContent() + " is saved") ;
//           return posts+ " saved";
           return new ResponseEntity<>(response, HttpStatus.CREATED);
       }
 return null;
    }

    @PutMapping("edit/{postId}/user1/{id}")
    public String editPost(@RequestBody PostDto posts,@PathVariable("id")Long userId,
                           @PathVariable("postId")Long postId){
        Users user1 = userServiceImpl.findById(userId).get();
//        editPost(Users user1, Long postId, String title, String content);
        if(postServiceImpl.editPost(user1, postId, posts.getTitle(), posts.getContent())){
            return posts+ " post edited";
        }
        return "error editing post";
    }

    @GetMapping(path = "getPostById/{id}")
    public Optional<Posts> getPostById(@PathVariable("id")Long postId){
        return postServiceImpl.findById(postId);
    }
    @GetMapping("/getAllPost")
    public List<Posts> getPostLists(){
      return postServiceImpl.findAllPosts();
    }

    @DeleteMapping("deleteById{id}")
    public String DeleteById(@PathVariable("id") Long id){
      postServiceImpl.deletePostById(id);
      return "post deleted: post_id "+id;
    }
}
