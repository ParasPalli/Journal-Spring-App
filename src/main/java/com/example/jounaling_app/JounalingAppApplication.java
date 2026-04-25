package com.example.jounaling_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JounalingAppApplication {

	public static void main(String[] args) {
		// Current Env/Profile
		ConfigurableApplicationContext context = SpringApplication.run(JounalingAppApplication.class, args);
		System.out.println(context.getEnvironment().getActiveProfiles()[0]);

		//
		SpringApplication.run(JounalingAppApplication.class, args);
	}

	// Adding PlatFormTransactionManager bean to enable @Transactional support
	@Bean
	public PlatformTransactionManager initializePlatFormTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTransactionManager(mongoDatabaseFactory);
	}

	// RestTemplate bean for making REST API calls
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
