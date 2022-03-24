package com.example.demo.search;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.demo.search.persist.jpa")
@Configuration
public class JpaConfig {

}
