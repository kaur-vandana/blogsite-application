package com.user.api.userserviceapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
// import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.ToString;

// @Getter
// @Setter
// @ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("User")
public class User implements Serializable {

    @Id
    @NotEmpty(message = "User Name is mandatory")
    private String username;

    @NotEmpty(message = "email id is mandatory")
    @Email(message = "Invalid Email format")
    private String emailId;

    @NotEmpty(message = "password is mandatory")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be alphanumeric and at least 8 characters long")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private Set<String> blogName;


    public User(@NotEmpty(message = "User Name is mandatory") String username,
            @NotEmpty(message = "email id is mandatory") @Email(message = "Invalid Email format") String emailId,
            @NotEmpty(message = "password is mandatory") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be alphanumeric and at least 8 characters long") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
        this.username = username;
        this.emailId = emailId;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getBlogName() {
        return blogName;
    }

    public void setBlogName(Set<String> blogName) {
        this.blogName = blogName;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", emailId=" + emailId + ", password=" + password + ", blogName="
                + blogName + "]";
    }

}
