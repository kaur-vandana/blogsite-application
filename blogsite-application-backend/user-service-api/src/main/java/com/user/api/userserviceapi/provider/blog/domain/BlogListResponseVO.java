package com.user.api.userserviceapi.provider.blog.domain;

import java.io.Serializable;
import java.util.List;

import com.user.api.userserviceapi.model.Blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogListResponseVO implements Serializable {
    private List<Blog> blogs;
}
