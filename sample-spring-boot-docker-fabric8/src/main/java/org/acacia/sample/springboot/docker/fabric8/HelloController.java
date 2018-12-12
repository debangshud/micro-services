package org.acacia.sample.springboot.docker.fabric8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello from container!!!";
    }
}