package com.backend.testing.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.backend.testing.model.domain")
@EnableJpaRepositories(basePackages = "com.backend.testing.model.repository")
@Configuration
public class JpaSpringConfiguration {
}
