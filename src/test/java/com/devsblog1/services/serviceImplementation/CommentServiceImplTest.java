package com.devsblog1.services.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsblog1.model.CommentDto;
import com.devsblog1.model.Comments;
import com.devsblog1.model.Posts;
import com.devsblog1.model.Users;
import com.devsblog1.repositories.CommentRepository;
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

@ContextConfiguration(classes = {CommentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

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
        when(this.commentRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Comments> actualFindByIdResult = this.commentServiceImpl.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.commentRepository).findById((Long) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testCreateComment() {
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
        Optional<Posts> ofResult1 = Optional.of(posts);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult1);

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

        Comments comments = new Comments();
        comments.setComment("Comment");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setId(123L);
        comments.setPosts(posts1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setUsers1(users3);
        when(this.commentRepository.save((Comments) any())).thenReturn(comments);
        this.commentServiceImpl.createComment(123L, 123L, new CommentDto("Comment"));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comments) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testCreateComment2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

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

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");

        Comments comments = new Comments();
        comments.setComment("Comment");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setId(123L);
        comments.setPosts(posts1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setUsers1(users2);
        when(this.commentRepository.save((Comments) any())).thenReturn(comments);
        this.commentServiceImpl.createComment(123L, 123L, new CommentDto("Comment"));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testCreateComment3() {
        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setFavoritePosts(new ArrayList<>());
        users.setGender("Gender");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        Optional<Users> ofResult = Optional.of(users);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.postRepository.findById((Long) any())).thenReturn(Optional.empty());

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

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");

        Comments comments = new Comments();
        comments.setComment("Comment");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setId(123L);
        comments.setPosts(posts);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setUsers1(users2);
        when(this.commentRepository.save((Comments) any())).thenReturn(comments);
        this.commentServiceImpl.createComment(123L, 123L, new CommentDto("Comment"));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testEditComment() {
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
        when(this.postRepository.getById((Long) any())).thenReturn(posts);

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

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setFavoritePosts(new ArrayList<>());
        users2.setGender("Gender");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");

        Comments comments = new Comments();
        comments.setComment("Comment");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setId(123L);
        comments.setPosts(posts1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comments.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        comments.setUsers1(users2);
        when(this.commentRepository.getById((Long) any())).thenReturn(comments);
        CommentDto comments1 = new CommentDto("Comment");

        Users users3 = new Users();
        users3.setEmail("jane.doe@example.org");
        users3.setFavoritePosts(new ArrayList<>());
        users3.setGender("Gender");
        users3.setId(123L);
        users3.setName("Name");
        users3.setPassword("iloveyou");
        this.commentServiceImpl.editComment(comments1, 123L, 123L, users3);
        verify(this.postRepository).getById((Long) any());
        verify(this.commentRepository).getById((Long) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testFindAllComments() {
        ArrayList<Comments> commentsList = new ArrayList<>();
        when(this.commentRepository.findAll()).thenReturn(commentsList);
        List<Comments> actualFindAllCommentsResult = this.commentServiceImpl.findAllComments();
        assertSame(commentsList, actualFindAllCommentsResult);
        assertTrue(actualFindAllCommentsResult.isEmpty());
        verify(this.commentRepository).findAll();
    }

    @Test
    void testDeleteCommentById() {
        doNothing().when(this.commentRepository).deleteById((Long) any());
        this.commentServiceImpl.deleteCommentById(123L);
        verify(this.commentRepository).deleteById((Long) any());
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }

    @Test
    void testDeleteAll() {
        doNothing().when(this.commentRepository).deleteAll();
        this.commentServiceImpl.deleteAll(new CommentDto("Comment"));
        verify(this.commentRepository).deleteAll();
        assertTrue(this.commentServiceImpl.findAllComments().isEmpty());
    }
}

