package io.github.guilhermeabroncari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {
    String test = "alterations";

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
