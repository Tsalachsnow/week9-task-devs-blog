package com.devsblog1.services.serviceImplementation;

import com.devsblog1.model.Post_Likes;
import com.devsblog1.repositories.Post_LikesRepository;
import com.devsblog1.services.Post_likeService;
import org.springframework.stereotype.Service;

@Service
public class Post_LikeImpl implements Post_likeService {
    Post_LikesRepository post_likes_repository;

    public Post_LikeImpl(Post_LikesRepository post_likes_repository) {
        this.post_likes_repository = post_likes_repository;
    }

    public void create(Post_Likes postlks) {
        post_likes_repository.save(postlks);
    }

    public void deleteById(Long id) {
        post_likes_repository.deleteById(id);
    }
}
