package com.blog.api.blogserviceapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.blogserviceapi.dao.BlogRepository;
import com.blog.api.blogserviceapi.model.Blog;
import com.blog.api.blogserviceapi.model.BlogListResponseVO;
import com.blog.api.blogserviceapi.service.BlogService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController {

    private Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/api/v1.0/blogsite/blogs/info/{category}")
    public ResponseEntity<List<Blog>> retrieveAllBlogsBasedOnCategory(@PathVariable String category) {
        List<Blog> blog = blogService.retrieveAllBlogsBasedOnCategory(category);
        ResponseEntity<List<Blog>> response = new ResponseEntity<>(blog, HttpStatus.OK);
        return response;
    }

    @GetMapping("/api/v1.0/blogsite/blogs/get/category/{from}/{to}")
    public List<Blog> retrieveAllBlogsBasedOnDateRange(@PathVariable String from, @PathVariable String to) {
        System.out.println(from);
        System.out.println(to);

        List<Blog> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Blog> blogs = (List<Blog>) blogRepository.findAll();

        for (Blog b : blogs) {
            String formattedString = b.getDate().format(formatter);
            if(formattedString.compareTo(from) >= 0 && formattedString.compareTo(to) <= 0) {
                result.add(b);
            } 
        }

        logger.info("date range from: {} to: {} blogs: {}", from, to, result);
        return result;
    }

    @PostMapping("/api/v1.0/blogsite/blogs/add")
    public Blog createBlogForUser(@RequestBody Blog blog) {
        blog.setDate(LocalDate.now());
        return blogRepository.save(blog);
    }

    @GetMapping("/api/v1.0/blogsite/blogs")
    public ResponseEntity<BlogListResponseVO> retrieveAllBlogs() {
        List<Blog> blog = blogService.retrieveAllBlogs();
        BlogListResponseVO responseVO = new BlogListResponseVO();
        responseVO.setBlogs(blog);

        ResponseEntity<BlogListResponseVO> response = new ResponseEntity<>(responseVO, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/api/v1.0/blogsite/blogs/deleteall")
    public void deleteAllUsers() {
        blogService.deleteAllBlogs();
    }

    @DeleteMapping("/api/v1.0/blogsite/blogs/delete/{blogname}")
    public void deleteBlog(@PathVariable String blogname) {
        blogService.deleteBlog(blogname);
    }

}
