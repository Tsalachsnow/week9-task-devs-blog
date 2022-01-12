package com.devsblog1.services.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsblog1.model.PostDto;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.repositories.PostRepository;
import com.devsblog1.repositories.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testEditPost() {
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

        Posts posts1 = new Posts();
        posts1.setComments(new ArrayList<>());
        posts1.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        posts1.setId(123L);
        posts1.setTitle("Dr");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        posts1.setUsers(users1);
        when(this.postRepository.save((Posts) any())).thenReturn(posts1);
        when(this.postRepository.getById((Long) any())).thenReturn(posts);

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        assertTrue(this.postServiceImpl.editPost(users2, 123L, "Dr", "Not all who wander are lost"));
        verify(this.postRepository).getById((Long) any());
        verify(this.postRepository).save((Posts) any());
        assertTrue(this.postServiceImpl.findAllPosts().isEmpty());
    }

    @Test
    void testMakePost() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        Optional<Users> ofResult = Optional.of(users);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setFavoritePosts(new ArrayList<>());
        users1.setGender("Gender");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");

        Posts posts = new Posts();
        posts.setComments(new ArrayList<>());
        posts.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        posts.setId(123L);
        posts.setTitle("Dr");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        posts.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        posts.setUsers(users1);
        when(this.postRepository.save((Posts) any())).thenReturn(posts);
        this.postServiceImpl.makePost(123L, new PostDto("Dr", "Not all who wander are lost"));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).save((Posts) any());
        assertTrue(this.postServiceImpl.findAllPosts().isEmpty());
    }

    @Test
    void testFindAllPosts() {
        ArrayList<Posts> postsList = new ArrayList<>();
        when(this.postRepository.findAll()).thenReturn(postsList);
        List<Posts> actualFindAllPostsResult = this.postServiceImpl.findAllPosts();
        assertSame(postsList, actualFindAllPostsResult);
        assertTrue(actualFindAllPostsResult.isEmpty());
        verify(this.postRepository).findAll();
    }

    @Test
    void testDeletePostById() {
        doNothing().when(this.postRepository).deleteById((Long) any());
        this.postServiceImpl.deletePostById(123L);
        verify(this.postRepository).deleteById((Long) any());
        assertTrue(this.postServiceImpl.findAllPosts().isEmpty());
    }

    @Test
    void testFindById() {
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
        Optional<Posts> ofResult = Optional.of(posts);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Posts> actualFindByIdResult = this.postServiceImpl.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.findAllPosts().isEmpty());
    }
}

