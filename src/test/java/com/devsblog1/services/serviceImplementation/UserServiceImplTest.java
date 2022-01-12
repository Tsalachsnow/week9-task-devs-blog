package com.devsblog1.services.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsblog1.model.Users;
import com.devsblog1.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testConstructor() {
        assertNull((new UserServiceImpl(mock(UserRepository.class))).findAllUsers());
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
        Optional<Users> ofResult = Optional.of(users);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Users> actualFindByIdResult = this.userServiceImpl.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testSaveUser() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        when(this.userRepository.findByEmail((String) any())).thenReturn(users);

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setFavoritePosts(new ArrayList<>());
        users1.setGender("Gender");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        assertNull(this.userServiceImpl.saveUser(users1));
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testAuthenticate() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(users);
        assertSame(users, this.userServiceImpl.authenticate("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
    }
}

