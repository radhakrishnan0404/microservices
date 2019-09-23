package com.svm.psms;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PSMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(PSMSApplication.class, args);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
	    HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
	    fact.setEntityManagerFactory(emf);
	    return fact;
	}
}
