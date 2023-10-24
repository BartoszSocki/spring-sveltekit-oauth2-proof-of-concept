package com.sockib.springresourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.sockib.springresourceserver.model.respository", repositoryImplementationPostfix = "Searchable")
public class JpaConfig {
}
