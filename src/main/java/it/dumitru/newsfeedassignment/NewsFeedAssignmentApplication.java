package it.dumitru.newsfeedassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement
public class NewsFeedAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFeedAssignmentApplication.class, args);
	}

}
