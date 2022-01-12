package com.devsblog1.repositories;

import com.devsblog1.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPassword(String email, String password);
    Optional<Users>findFirstByEmail(String email);
    Users findByEmail(String email);

//    @Modifying
//    @Query
//   ("SELECT id FROM Users WHERE id=?1")
//    Users findUserById(Long id);
}
