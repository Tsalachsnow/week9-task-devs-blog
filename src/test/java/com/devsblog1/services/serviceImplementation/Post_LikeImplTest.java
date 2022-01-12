package com.devsblog1.services.serviceImplementation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsblog1.model.Post_Likes;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.repositories.Post_LikesRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Post_LikeImpl.class})
@ExtendWith(SpringExtension.class)
class Post_LikeImplTest {
    @Autowired
    private Post_LikeImpl post_LikeImpl;

    @MockBean
    private Post_LikesRepository post_LikesRepository;

    @Test
    void testCreate() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");

        Posts posts = new Posts();
        posts.setComments(new ArrayList<>());
        posts.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        posts.setId(123L);
        posts.setTitle("Dr");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        posts.setUsers(users);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setFavoritePosts(new ArrayList<>());
        users1.setGender("Gender");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");

        Post_Likes post_Likes = new Post_Likes();
        post_Likes.setId(123L);
        post_Likes.setLikes(1);
        post_Likes.setPosts(posts);
        post_Likes.setUsers(users1);
        when(this.post_LikesRepository.save((Post_Likes) any())).thenReturn(post_Likes);

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");

        Posts posts1 = new Posts();
        posts1.setComments(new ArrayList<>());
        posts1.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        posts1.setId(123L);
        posts1.setTitle("Dr");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        posts1.setUsers(users2);

        Users users3 = new Users();
        users3.setEmail("jane.doe@example.org");
        users3.setFavoritePosts(new ArrayList<>());
        users3.setGender("Gender");
        users3.setId(123L);
        users3.setName("Name");
        users3.setPassword("iloveyou");

        Post_Likes post_Likes1 = new Post_Likes();
        post_Likes1.setId(123L);
        post_Likes1.setLikes(1);
        post_Likes1.setPosts(posts1);
        post_Likes1.setUsers(users3);
        this.post_LikeImpl.create(post_Likes1);
        verify(this.post_LikesRepository).save((Post_Likes) any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(this.post_LikesRepository).deleteById((Long) any());
        this.post_LikeImpl.deleteById(123L);
        verify(this.post_LikesRepository).deleteById((Long) any());
    }
}

