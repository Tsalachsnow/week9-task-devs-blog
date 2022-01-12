package com.devsblog1.services.serviceImplementation;

import com.devsblog1.model.Users;
import com.devsblog1.repositories.UserRepository;
import com.devsblog1.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> findAllUsers() {
        return null;
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Users saveUser(Users users) {
       Users userDB = userRepository.findByEmail(users.getEmail());
       if(userDB == null) {
           System.out.println(users);
           userRepository.save(users);
           return users;
       }
        System.out.println("no user saved");
       return null;
    }

    @Override
    public Users authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
