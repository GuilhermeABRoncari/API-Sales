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
            clientRepository.persist(new Client("Guilherme"));
            clientRepository.persist(new Client("Raniere"));

            System.out.println("Read");
            List<Client> clientList = clientRepository.findAll();
            clientList.forEach(System.out::println);

            System.out.println("Find by name");
            clientRepository.findByName("Gui").forEach(System.out::println);

            System.out.println("Update");
            clientList.forEach(client -> {
                client.setName(client.getName() + " UPDATED");
                clientRepository.update(client);
            });
            clientList.forEach(System.out::println);

            System.out.println("Delete");
            clientRepository.findAll().forEach(client -> clientRepository.delete(client));

            clientList = clientRepository.findAll();
            if (clientList.isEmpty()) {
                System.out.println("No Client found...");
            } else {
                clientList.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
