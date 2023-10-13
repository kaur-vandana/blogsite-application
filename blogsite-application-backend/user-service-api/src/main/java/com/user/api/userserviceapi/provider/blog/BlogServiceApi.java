package com.user.api.userserviceapi.provider.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.user.api.userserviceapi.model.Blog;
import com.user.api.userserviceapi.provider.blog.domain.BlogListResponseVO;
import com.user.api.userserviceapi.provider.blog.domain.BlogResponseVO;

import reactor.core.publisher.Mono;

@Component
public class BlogServiceApi {

    @Autowired
    private WebClient.Builder webClientBuilder;


    public BlogServiceApi(Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<BlogResponseVO> postABlog(Blog blog) {
        return webClientBuilder.build()
        .post()
        .uri("http://127.0.0.1:8085/api/v1.0/blogsite/blogs/add")
        .bodyValue(blog)
        .retrieve()
        .bodyToMono(BlogResponseVO.class);
    }

    public Mono<BlogListResponseVO> getBlogList() {
        return webClientBuilder.build()
        .get()
        .uri("http://localhost:8085/api/v1.0/blogsite/blogs")
        .retrieve()
        .bodyToMono(BlogListResponseVO.class);

    }

    public Void deleteBlog(String blogname) {

        return webClientBuilder.build()
        .delete()
        .uri("http://localhost:8085/api/v1.0/blogsite/blogs/delete/" + blogname)
        .retrieve()
        .toBodilessEntity().block().getBody();
        
    }
        
    
}
