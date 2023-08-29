package org.jsp.project.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan("org.jsp")
public class FoodOrderConfig {
	@Bean
	public EntityManager  getEntityManager() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
