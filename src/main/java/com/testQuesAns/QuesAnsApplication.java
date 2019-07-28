package com.testQuesAns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QuesAnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesAnsApplication.class, args);
	}

}
