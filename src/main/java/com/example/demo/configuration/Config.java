package com.example.demo.configuration;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.FilterService;
import com.example.demo.Services.PagingService;
import com.example.demo.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name="userRepoBean")
    UserRepository userRepository(){
        return new UserRepository();
    }

    @Bean(name="userServiceBean")
    UserService userService (){
        return new UserService();
    }

    @Bean(name="pagingServiceBean")
    PagingService pagingService() { return new PagingService(); }

    @Bean(name="filterServiceBean")
    FilterService filterService() { return new FilterService(); }
}
