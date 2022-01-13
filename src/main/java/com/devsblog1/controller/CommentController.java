package com.devsblog1.controller;

import com.devsblog1.model.*;
import com.devsblog1.services.serviceImplementation.CommentServiceImpl;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    CommentServiceImpl commentServiceImpl;
    PostServiceImpl postServiceImpl;
    UserServiceImpl userServiceImpl;

    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping(path = "/makeComment/{id}/{postId}")
    public ResponseEntity<Response> makeComment(@RequestBody CommentDto comment, @PathVariable Long id, @PathVariable Long postId){
        Users user1 = userServiceImpl.findById(id).get();
        if(user1 != null){
//            System.out.println("i am here now======");
                commentServiceImpl.createComment(id,postId,comment);
            Response commentResponse = new Response();
            commentResponse.setResponse(comment + " made by "+user1.getName()+" created successfully");
            return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
        }
      return null;
    }

    @PutMapping("edit/{commentId}/posts/{postId}/users/{userId}")
    public ResponseEntity<Response> editPost(@RequestBody CommentDto comments, @PathVariable("commentId")Long commentId,
                                             @PathVariable("postId")Long postId, @PathVariable Long userId) {
        Users users = userServiceImpl.findById(userId).get();
        commentServiceImpl.editComment(comments, commentId, postId,users);

        commentServiceImpl.editComment(comments, commentId, postId, users);
            Response commentResponse = new Response();
            commentResponse.setResponse(comments + " was edited successfully");
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);

    }

    @GetMapping(path = "getCommentById/{id}")
    public Optional<Comments> getCommentById(@PathVariable("id")Long commentId){
        return commentServiceImpl.findById(commentId);
    }

    @GetMapping("/getAllComments")
    public List<Comments> getCommentLists(){
        return commentServiceImpl.findAllComments();
    }

    @DeleteMapping("/deleteById/{id}")
    public String DeleteById(@PathVariable("id") Long id){
        commentServiceImpl.deleteCommentById(id);
        return "["+id+"]"+" comment deleted successfully";
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(@RequestBody CommentDto comments){
        if (comments != null) {
            commentServiceImpl.deleteAll(comments);
            Response commentResponse = new Response();
            commentResponse.setResponse("comment deleted successfully") ;
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);
        }
        return null;
    }

}
