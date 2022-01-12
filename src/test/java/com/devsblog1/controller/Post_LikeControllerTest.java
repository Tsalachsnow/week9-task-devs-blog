package com.devsblog1.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.Post_LikeImpl;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Post_LikeController.class})
@ExtendWith(SpringExtension.class)
class Post_LikeControllerTest {
    @MockBean
    private PostServiceImpl postServiceImpl;

    @Autowired
    private Post_LikeController post_LikeController;

    @MockBean
    private Post_LikeImpl post_LikeImpl;

    @Test
    void testDecrementLikes() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/decrementLikes/{postId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.post_LikeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void testGetLikesByPostId() throws Exception {
        doNothing().when(this.post_LikeImpl).create((com.devsblog1.model.Post_Likes) any());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getLikes/{postId}", 123L);
        MockMvcBuilders.standaloneSetup(this.post_LikeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"response\":\"you have Post_Likes(id=null, likes=0, posts=null, users=null) now\"}"));
    }
}

