package com.devsblog1.services;

import com.devsblog1.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Optional<Comments> findById(Long id);
    void createComment(Long id, Long postId, CommentDto comments);
     void editComment(CommentDto comments, Long id, Long postId, Users users);
    List<Comments> findAllComments();
    void deleteCommentById(Long id);
    void deleteAll(CommentDto comments);
}
