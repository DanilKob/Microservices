package home.project.simpleeurekaclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleEurekaClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleEurekaClientServiceApplication.class, args);
	}

}
