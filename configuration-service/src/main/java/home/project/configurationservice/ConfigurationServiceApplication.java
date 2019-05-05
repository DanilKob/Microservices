package home.project.configurationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigServer
/*
annotation
will only reload the custom Spring properties you have in your application configura-
tion. Items such as your database configuration that are used by Spring Data won’t be
reloaded by the @RefreshScope annotation. To perform the refresh, you can hit the
http://<yourserver>:8080/refresh endpoint.
 */
@RefreshScope
public class ConfigurationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServiceApplication.class, args);
	}

}
