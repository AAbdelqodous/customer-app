package com.elite.customer_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.DriverManager;

@SpringBootApplication
@ComponentScan(basePackages = "com.elite.customer_app")
@EntityScan(basePackages = "com.elite.customer_app.model")
@EnableJpaRepositories(basePackages = "com.elite.customer_app.repository")
public class CustomerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApplication.class, args);

		/*String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "springstudent";
		String password = "P@ssw0rd";

		try {
			System.out.println("Connecting to database: " + url);

			DriverManager.getConnection(url, user, password);

			System.out.println("Connection successfully!");

		}catch(Exception e) {
			e.printStackTrace();
		}*/
	}

}
