package com.devsblog1.services;

import com.devsblog1.model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<Users> findAllUsers();
    Optional<Users> findById(Long id);
    Users saveUser(Users users);

    Users authenticate(String email, String password);

}
