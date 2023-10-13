package com.user.api.userserviceapi.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RedisHash("Blog")
public class Blog {

    @Id
    @NotEmpty(message = "Blog Name is mandatory")
    @Size(min=20, message = "username should have at least 20 characters")
    private String blogName;

    @Size(min=20, message = "category should have at least 20 characters")
    @NotEmpty(message = "category is mandatory")
    private String category;

    @Size(min=10, message = "article should have at least 10 characters")
    @NotEmpty(message = "article is mandatory")
    private String article;

    @NotEmpty(message = "author Name is mandatory")
    private String authorName;

    private LocalDate date;

}
