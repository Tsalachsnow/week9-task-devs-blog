package com.devsblog1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsblog1.model.PostDto;
import com.devsblog1.model.PostResponse;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.repositories.PostRepository;
import com.devsblog1.repositories.UserRepository;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @Autowired
    private PostController postController;

    @MockBean
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testMakePost() {
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
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.save((Posts) any())).thenReturn(posts);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setFavoritePosts(new ArrayList<>());
        users1.setGender("Gender");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(users1));
        PostServiceImpl postServiceImpl = new PostServiceImpl(postRepository, userRepository);

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        UserRepository userRepository1 = mock(UserRepository.class);
        when(userRepository1.findById((Long) any())).thenReturn(Optional.of(users2));
        PostController postController = new PostController(postServiceImpl, new UserServiceImpl(userRepository1));
        ResponseEntity<PostResponse> actualMakePostResult = postController
                .makePost(new PostDto("Dr", "Not all who wander are lost"), 123L);
        assertEquals("<201 CREATED Created,PostResponse(response=This text Dr this content Not all who wander are lost is"
                + " saved),[]>", actualMakePostResult.toString());
        assertTrue(actualMakePostResult.getHeaders().isEmpty());
        assertTrue(actualMakePostResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualMakePostResult.getStatusCode());
        assertEquals("PostResponse(response=This text Dr this content Not all who wander are lost is saved)",
                actualMakePostResult.getBody().toString());
        verify(postRepository).save((Posts) any());
        verify(userRepository).findById((Long) any());
        verify(userRepository1).findById((Long) any());
        assertTrue(postController.postServiceImpl.findAllPosts().isEmpty());
    }

    @Test
    void testMakePost2() {
        PostServiceImpl postServiceImpl = mock(PostServiceImpl.class);
        doNothing().when(postServiceImpl).makePost((Long) any(), (PostDto) any());

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(users));
        PostController postController = new PostController(postServiceImpl, new UserServiceImpl(userRepository));
        ResponseEntity<PostResponse> actualMakePostResult = postController
                .makePost(new PostDto("Dr", "Not all who wander are lost"), 123L);
        assertEquals("<201 CREATED Created,PostResponse(response=This text Dr this content Not all who wander are lost is"
                + " saved),[]>", actualMakePostResult.toString());
        assertTrue(actualMakePostResult.getHeaders().isEmpty());
        assertTrue(actualMakePostResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualMakePostResult.getStatusCode());
        assertEquals("PostResponse(response=This text Dr this content Not all who wander are lost is saved)",
                actualMakePostResult.getBody().toString());
        verify(postServiceImpl).makePost((Long) any(), (PostDto) any());
        verify(userRepository).findById((Long) any());
        assertTrue(postController.getPostLists().isEmpty());
    }

    @Test
    void testMakePost3() {
        PostServiceImpl postServiceImpl = mock(PostServiceImpl.class);
        doNothing().when(postServiceImpl).makePost((Long) any(), (PostDto) any());

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.findById((Long) any())).thenReturn(Optional.of(users));
        PostController postController = new PostController(postServiceImpl, userServiceImpl);
        ResponseEntity<PostResponse> actualMakePostResult = postController
                .makePost(new PostDto("Dr", "Not all who wander are lost"), 123L);
        assertEquals("<201 CREATED Created,PostResponse(response=This text Dr this content Not all who wander are lost is"
                + " saved),[]>", actualMakePostResult.toString());
        assertTrue(actualMakePostResult.getHeaders().isEmpty());
        assertTrue(actualMakePostResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualMakePostResult.getStatusCode());
        assertEquals("PostResponse(response=This text Dr this content Not all who wander are lost is saved)",
                actualMakePostResult.getBody().toString());
        verify(postServiceImpl).makePost((Long) any(), (PostDto) any());
        verify(userServiceImpl).findById((Long) any());
        assertTrue(postController.getPostLists().isEmpty());
    }

    @Test
    void testDeleteById() throws Exception {
        doNothing().when(this.postServiceImpl).deletePostById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteById{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("post deleted: post_id 123"));
    }

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
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.save((Posts) any())).thenReturn(posts1);
        when(postRepository.getById((Long) any())).thenReturn(posts);
        PostServiceImpl postServiceImpl = new PostServiceImpl(postRepository, mock(UserRepository.class));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(users2));
        PostController postController = new PostController(postServiceImpl, new UserServiceImpl(userRepository));
        assertEquals("PostDto(title=Dr, content=Not all who wander are lost) post edited",
                postController.editPost(new PostDto("Dr", "Not all who wander are lost"), 123L, 123L));
        verify(postRepository).getById((Long) any());
        verify(postRepository).save((Posts) any());
        verify(userRepository).findById((Long) any());
        assertTrue(postController.postServiceImpl.findAllPosts().isEmpty());
    }

    @Test
    void testEditPost2() {
        PostServiceImpl postServiceImpl = mock(PostServiceImpl.class);
        when(postServiceImpl.editPost((Users) any(), (Long) any(), (String) any(), (String) any())).thenReturn(true);

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(users));
        PostController postController = new PostController(postServiceImpl, new UserServiceImpl(userRepository));
        assertEquals("PostDto(title=Dr, content=Not all who wander are lost) post edited",
                postController.editPost(new PostDto("Dr", "Not all who wander are lost"), 123L, 123L));
        verify(postServiceImpl).editPost((Users) any(), (Long) any(), (String) any(), (String) any());
        verify(userRepository).findById((Long) any());
        assertTrue(postController.getPostLists().isEmpty());
    }

    @Test
    void testEditPost3() {
        PostServiceImpl postServiceImpl = mock(PostServiceImpl.class);
        when(postServiceImpl.editPost((Users) any(), (Long) any(), (String) any(), (String) any())).thenReturn(false);

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(users));
        PostController postController = new PostController(postServiceImpl, new UserServiceImpl(userRepository));
        assertEquals("error editing post",
                postController.editPost(new PostDto("Dr", "Not all who wander are lost"), 123L, 123L));
        verify(postServiceImpl).editPost((Users) any(), (Long) any(), (String) any(), (String) any());
        verify(userRepository).findById((Long) any());
        assertTrue(postController.getPostLists().isEmpty());
    }

    @Test
    void testEditPost4() {
        PostServiceImpl postServiceImpl = mock(PostServiceImpl.class);
        when(postServiceImpl.editPost((Users) any(), (Long) any(), (String) any(), (String) any())).thenReturn(true);

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.findById((Long) any())).thenReturn(Optional.of(users));
        PostController postController = new PostController(postServiceImpl, userServiceImpl);
        assertEquals("PostDto(title=Dr, content=Not all who wander are lost) post edited",
                postController.editPost(new PostDto("Dr", "Not all who wander are lost"), 123L, 123L));
        verify(postServiceImpl).editPost((Users) any(), (Long) any(), (String) any(), (String) any());
        verify(userServiceImpl).findById((Long) any());
        assertTrue(postController.getPostLists().isEmpty());
    }

    @Test
    void testDeleteById2() throws Exception {
        doNothing().when(this.postServiceImpl).deletePostById((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/deleteById{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("post deleted: post_id 123"));
    }

    @Test
    void testGetPostById() throws Exception {
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
        when(this.postServiceImpl.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getPostById/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":0,\"updatedAt\":0,\"id\":123,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"users\":{"
                                        + "\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"gender\":\"Gender\","
                                        + "\"favoritePosts\":[]},\"comments\":[]}"));
    }

    @Test
    void testGetPostLists() throws Exception {
        when(this.postServiceImpl.findAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllPost");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPostLists2() throws Exception {
        when(this.postServiceImpl.findAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getAllPost");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

