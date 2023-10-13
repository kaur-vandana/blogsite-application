package com.blog.api.blogserviceapi.model;

import java.io.Serializable;
import java.util.List;

public class BlogListResponseVO implements Serializable {
    private List<Blog> blogs;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "BlogListResponseVO [blogs=" + blogs + "]";
    }

    
}
