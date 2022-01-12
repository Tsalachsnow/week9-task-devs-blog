package com.devsblog1.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.devsblog1.model.CommentDto;
import com.devsblog1.model.Comments;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.services.serviceImplementation.CommentServiceImpl;
import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testDeleteAll() throws Exception {
        doNothing().when(this.commentServiceImpl).deleteAll((CommentDto) any());

        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        String content = (new ObjectMapper()).writeValueAsString(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"response\":\"comment deleted successfully\"}"));
    }

    @Test
    void testDeleteById() throws Exception {
        doNothing().when(this.commentServiceImpl).deleteCommentById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteById/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("[123] comment deleted successfully"));
    }

    @Test
    void testDeleteById2() throws Exception {
        doNothing().when(this.commentServiceImpl).deleteCommentById((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/deleteById/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("[123] comment deleted successfully"));
    }

    @Test
    void testEditPost() throws Exception {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        Optional<Users> ofResult = Optional.of(users);
        when(this.userServiceImpl.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(this.commentServiceImpl)
                .editComment((CommentDto) any(), (Long) any(), (Long) any(), (Users) any());

        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        String content = (new ObjectMapper()).writeValueAsString(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/edit/{commentId}/posts/{postId}/users/{userId}", 123L, 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"response\":\"CommentDto(comment=Comment) was edited successfully\"}"));
    }

    @Test
    void testGetCommentById() throws Exception {
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

        Comments comments = new Comments();
        comments.setComment("Comment");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setId(123L);
        comments.setPosts(posts);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setUsers1(users1);
        Optional<Comments> ofResult = Optional.of(comments);
        when(this.commentServiceImpl.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCommentById/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":0,\"updatedAt\":0,\"id\":123,\"comment\":\"Comment\",\"users1\":{\"id\":123,\"name\":\"Name\",\"email\":"
                                        + "\"jane.doe@example.org\",\"password\":\"iloveyou\",\"gender\":\"Gender\",\"favoritePosts\":[]}}"));
    }

    @Test
    void testGetCommentLists() throws Exception {
        when(this.commentServiceImpl.findAllComments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllComments");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testMakeComment() throws Exception {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        Optional<Users> ofResult = Optional.of(users);
        when(this.userServiceImpl.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(this.commentServiceImpl).createComment((Long) any(), (Long) any(), (CommentDto) any());

        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        String content = (new ObjectMapper()).writeValueAsString(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/makeComment/{id}/{postId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"response\":\"CommentDto(comment=Comment) made by Name created successfully\"}"));
    }
}

