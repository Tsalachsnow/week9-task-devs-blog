package com.devsblog1.services;

import com.devsblog1.model.PostDto;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Optional<Posts> findById(Long id);
     boolean editPost(Users user1, Long postId, String title, String content);

    void makePost(Long id, PostDto posts);

    List<Posts> findAllPosts();
    void deletePostById(Long id);
//    Posts savePost(Posts posts);

}
