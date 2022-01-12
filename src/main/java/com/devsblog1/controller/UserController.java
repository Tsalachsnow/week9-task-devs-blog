package com.devsblog1.controller;

import com.devsblog1.model.Users;
//import com.devsblog1.services.serviceImplementation.PostServiceImpl;
import com.devsblog1.services.serviceImplementation.UserServiceImpl;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/blog")
public class UserController {

    UserServiceImpl userServiceimpl;
//    PostServiceImpl postserviceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
//        this.postserviceImpl = postserviceImpl;
    }

    @PostMapping("/signup")
    public Users save(@RequestBody Users users) {
       Users user1 = userServiceimpl.saveUser(users);
       return user1;
    }

    @GetMapping("/login")
    public String login(@RequestBody Users users){
       Users user2 = userServiceimpl.authenticate(users.getEmail(), users.getPassword());
        return(user2 != null)? user2+"logged in":"incorrect details!!";
    }



}
