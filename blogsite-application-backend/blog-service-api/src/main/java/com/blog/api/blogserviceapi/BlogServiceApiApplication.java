package com.blog.api.blogserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@EnableEurekaClient
@SpringBootApplication
public class BlogServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceApiApplication.class, args);
	}


	// @Bean
    // public CorsWebFilter corsWebFilter() {
    //     CorsConfiguration corsConfig = new CorsConfiguration();
    //     corsConfig.addAllowedOrigin("*"); // Replace with your specific allowed origins
    //     corsConfig.addAllowedMethod("*"); // Allow all HTTP methods
    //     corsConfig.addAllowedHeader("*"); // Allow all headers
    //     corsConfig.setAllowCredentials(true); // Allow cookies

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", corsConfig);

    //     return new CorsWebFilter(source);
    // }

}
