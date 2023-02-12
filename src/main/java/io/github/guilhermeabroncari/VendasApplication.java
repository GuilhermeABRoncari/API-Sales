package io.github.guilhermeabroncari;

import io.github.guilhermeabroncari.domain.entity.Client;
import io.github.guilhermeabroncari.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
        return args -> {
            System.out.println("Create");
            clientRepository.save(new Client("Guilherme"));
            clientRepository.save(new Client("Raniere"));

            var name = clientRepository.existsByName("Guilherme");
            System.out.println(name);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
