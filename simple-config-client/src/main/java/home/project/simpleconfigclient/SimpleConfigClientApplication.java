package home.project.simpleconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleConfigClientApplication.class, args);
    }

}
