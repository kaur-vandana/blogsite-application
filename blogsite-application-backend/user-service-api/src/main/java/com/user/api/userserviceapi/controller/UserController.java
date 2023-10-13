package com.user.api.userserviceapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.api.userserviceapi.model.User;

import com.user.api.userserviceapi.model.Blog;
import com.user.api.userserviceapi.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1.0/blogsite/user/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User savedUser = userService.registerUser(user);

        ResponseEntity<User> responseEntity = new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
        logger.info("saved user: {}", user);
        return responseEntity;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        List<User> users = userService.retrieveAllUsers();
        logger.info("list of users: {}", users);
        return users;
    }


     @PostMapping("/users")
    public boolean isUserPresent(@RequestBody User user) {
        List<User> users = userService.retrieveAllUsers();
        for(User x: users) {
            if(user.getUsername().equals(x.getUsername()) && user.getPassword().equals(x.getPassword())) {
                return true;
            }
        }
        logger.info("list of users: {}", users);
        throw new RuntimeException("user not found");
        // return false;
    }

    @PostMapping("/api/v1.0/blogsite/user/blogs/add/{blogname}")
    public Blog addBlog(@PathVariable String blogname, @Valid @RequestBody Blog blog, HttpServletRequest request) {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        return userService.addBlog(blogname, blog, username, password);
    }

    @GetMapping("/api/v1.0/blogsite/user/getall")
    public ResponseEntity<List<Blog>> getAllBlogsForUser(HttpServletRequest request) {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        List<Blog> blogs = userService.getAllBlogsForUser(username, password);
        ResponseEntity<List<Blog>> responseEntity = new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/api/v1.0/blogsite/user/deleteall")
    public void deleteAllUsers() {
        userService.deleteAllBlogs();
    }

    @DeleteMapping("/api/v1.0/blogsite/user/delete/{blogname}")
    public void deleteBlogForUser(@PathVariable String blogname, HttpServletRequest request) {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        userService.deleteBlogForUser(blogname, username, password);
    }

}
