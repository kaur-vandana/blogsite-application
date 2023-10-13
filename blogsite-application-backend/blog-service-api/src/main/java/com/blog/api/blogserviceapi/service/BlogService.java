package com.blog.api.blogserviceapi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.blogserviceapi.dao.BlogRepository;
import com.blog.api.blogserviceapi.model.Blog;

@Service
public class BlogService {

    private Logger logger = LoggerFactory.getLogger(BlogService.class);

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> retrieveAllBlogsBasedOnCategory(String category) {
        List<Blog> blogs =  (List<Blog>) blogRepository.findAll();
       List<Blog> result = new ArrayList<>();
       for(Blog b : blogs) {
          if( b.getCategory().equals(category))
          result.add(b);
       }
       logger.info("blogs list: {} for category: {}", result, category);
       return result;
    }

    public List<Blog> retrieveAllBlogs() {
        List<Blog> blogs =  (List<Blog>) blogRepository.findAll();
        logger.info("blogs list: {}", blogs);
        return blogs;
    }

    public void deleteAllBlogs() {
        blogRepository.deleteAll();
    }

    public void deleteBlog(String blogname) {
        blogRepository.deleteById(blogname);
    }
    
}
