package com.devsblog1.controller;

import com.devsblog1.model.Users;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testLogin() throws Exception {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("12345");
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testSave() throws Exception {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("12345");
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/blog/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}

