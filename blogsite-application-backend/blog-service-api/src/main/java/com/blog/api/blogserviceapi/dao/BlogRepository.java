package com.blog.api.blogserviceapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.api.blogserviceapi.model.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog, String> {

}
