package home.project.simpleconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Value("${cloud.configuration.init.message}")
    private String hello;
    @GetMapping(path = "/hello")
    public String hello(){
        return hello;
    }
}
