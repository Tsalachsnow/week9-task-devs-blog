package com.devsblog1.services.serviceImplementation;

import com.devsblog1.model.*;
import com.devsblog1.repositories.CommentRepository;
import com.devsblog1.repositories.PostRepository;
import com.devsblog1.repositories.UserRepository;
import com.devsblog1.services.CommentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Comments> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void createComment(Long id, Long postId, CommentDto comments) {
        var users1 = userRepository.findById(id);
        var posts = postRepository.findById(postId);
        Comments comment = new Comments();
        comment.setComment(comments.getComment());
        if (posts.isPresent() && users1.isPresent()) {
            comment.setPosts(posts.get());
            comment.setUsers1(users1.get());
            commentRepository.save(comment);
        }
    }

    @Override
    public void editComment(CommentDto comments, Long id, Long postId, Users users) {
        Comments comment1 = commentRepository.getById(id);
        Posts posts = postRepository.getById(postId);
        if(comment1.getPosts()== posts) {
            comment1.setComment(comments.getComment());
            commentRepository.save(comment1);
        }
    }

    public List<Comments> findAllComments() {
        return commentRepository.findAll();
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteAll(CommentDto comments) {
        commentRepository.deleteAll();
    }
}
