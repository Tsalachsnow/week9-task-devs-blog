package com.devsblog1.repositories;

import com.devsblog1.model.Comments;
import com.devsblog1.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
//    Optional<Users>findByEmail(String email);

}
