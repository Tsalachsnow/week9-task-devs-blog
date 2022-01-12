package com.devsblog1.services;

import com.devsblog1.model.Post_Likes;
import org.springframework.stereotype.Service;

@Service
public interface Post_likeService {

    void create(Post_Likes postlks);
}
