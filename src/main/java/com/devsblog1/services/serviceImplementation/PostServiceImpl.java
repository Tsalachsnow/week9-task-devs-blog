package com.devsblog1.services.serviceImplementation;

import com.devsblog1.model.PostDto;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.repositories.PostRepository;
import com.devsblog1.repositories.UserRepository;
import com.devsblog1.services.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    UserRepository userRepository;

   @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public Posts findById(Long id) {
//        return null;
//    }


//    public void makePost(Long userId, PostDto posts){
//       Users users1 = userRepository.findById(userId).orElseThrow(NullPointerException::new);
//       Posts post = new Posts();
//       post.setTitle(posts.getTitle());
//       post.setContent(posts.getContent());
//
//       if(users1 != null){
//           post.setUsers(users1);
//           postRepository.save(post);
//       }
//    }

    @Override
    public boolean editPost(Users user1, Long postId, String title, String content) {
        Posts posts = postRepository.getById(postId);
        posts.setTitle(title);
        posts.setContent(content);
        postRepository.save(posts);
        return true;
    }

    @Override
    public void makePost(Long id, PostDto posts) {
        Users users1 = userRepository.findById(id).orElseThrow(NullPointerException::new);
       Posts post = new Posts();
       post.setTitle(posts.getTitle());
       post.setContent(posts.getContent());

       if(users1 != null){
           post.setUsers(users1);
           postRepository.save(post);
       }
    }

    @Override
    public List<Posts> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }


    @Override
    public Optional<Posts> findById(Long postId) {
        return postRepository.findById(postId);
    }
}
