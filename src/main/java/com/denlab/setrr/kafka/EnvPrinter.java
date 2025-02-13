package com.denlab.setrr.kafka;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EnvPrinter {
    private final Environment environment;

    public EnvPrinter(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void printEnvVariables() {
        System.out.println("===== Environment Variables =====");
        Arrays.stream(environment.getActiveProfiles()).forEach(profile ->
                System.out.println("Active Profile: " + profile)
        );

        System.getenv().forEach((key, value) -> System.out.println(key + "=" + value));
    }
}