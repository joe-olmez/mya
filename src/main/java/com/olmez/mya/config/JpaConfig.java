package com.olmez.mya.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.olmez.mya.DatabaseModule;

@Configuration
@EnableJpaRepositories(basePackageClasses = DatabaseModule.class)
@EntityScan(basePackages = DatabaseModule.ENTITIES_PACKAGE)
public class JpaConfig {

}
