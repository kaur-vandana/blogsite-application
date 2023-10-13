package com.user.api.userserviceapi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.api.userserviceapi.model.Blog;
import com.user.api.userserviceapi.model.User;
import com.user.api.userserviceapi.provider.blog.BlogServiceApi;
import com.user.api.userserviceapi.provider.blog.domain.BlogListResponseVO;
import com.user.api.userserviceapi.provider.blog.domain.BlogResponseVO;
import com.user.api.userserviceapi.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogServiceApi blogServiceApi;

    public UserService(UserRepository userRepository, BlogServiceApi blogServiceApi) {
        this.userRepository = userRepository;
        this.blogServiceApi = blogServiceApi;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> retrieveAllUsers() {
       return (List<User>) userRepository.findAll();
    }

    public Blog addBlog(String blogname, Blog blog,  String username, String password) {

        User user = validateUser(username, password);
        if(null == user) {
            throw new RuntimeException("already exits");
        }

        Set<String> blogNames = new HashSet();
        User addBlogToUser = user;

        if(null == user.getBlogName()) { 
            blogNames.add(blogname);
        } else {
            blogNames.addAll(user.getBlogName());
            blogNames.add(blogname);
        }

        blogNames.add(blogname);

        addBlogToUser.setBlogName(blogNames);

        registerUser(addBlogToUser);

        Mono<BlogResponseVO>  response = blogServiceApi.postABlog(blog);
        return response.block().getBlogs();
       
    }

    private User validateUser(String username, String password) {
        List<User> users = retrieveAllUsers();

        for(User user : users) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public List<Blog> getAllBlogsForUser(String username, String password) {
        User user = validateUser(username, password);
        Set<String> blognames= user.getBlogName();
        
        Mono<BlogListResponseVO> responseVO = blogServiceApi.getBlogList();
        BlogListResponseVO response = responseVO.block();
        List<Blog> blogList = response.getBlogs();

        List<Blog> blogs = new ArrayList<>();

        if(blogList.size() > 0 && blognames.size() > 0) {
            for(Blog b : blogList) {
                for(String s : blognames) {
                    if(b.getBlogName().equals(s)) {
                        blogs.add(b);
                    }
                }
            }
        }
        
        return blogs;
    }


    public void deleteAllBlogs() {
        userRepository.deleteAll();
    }


    public void deleteBlogForUser(String blogname, String username, String password) {
        blogServiceApi.deleteBlog(blogname);
      
        User user = validateUser(username, password);
        Set<String> blognames= user.getBlogName();
        if(blognames.size() > 0) {
            for(String s : blognames) {
                if(blogname.equals(s)) {
                    blognames.remove(s);
                    break;
                }
            }
        }
        user.setBlogName(blognames);
        registerUser(user);
        return;
    }


    
}
