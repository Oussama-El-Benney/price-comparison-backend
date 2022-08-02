package org.sid.pricecomparisonbackend;

import org.sid.pricecomparisonbackend.entities.*;
import org.sid.pricecomparisonbackend.repositories.MagasinProductRepository;
import org.sid.pricecomparisonbackend.repositories.MagasinRepository;
import org.sid.pricecomparisonbackend.repositories.PersonRepository;
import org.sid.pricecomparisonbackend.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PriceComparisonBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceComparisonBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner start(PersonRepository personRepository,
                          MagasinProductRepository magasinProductRepository,
                          ProductRepository productRepository,
                          MagasinRepository magasinRepository) {
    return args -> {
      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
        Person customer = new Person();
        customer.setName(name);
        customer.setUsername(name + "-kun");
        customer.setEmail(name + "@gmail.com");
        personRepository.save(customer);
      });
      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
        Client client = new Client();
        client.setName(name);
        client.setUsername(name + "-kun");
        client.setEmail(name + "@gmail.com");
        client.setTelNumber(29004323);
        personRepository.save(client);
      });
      Admin admin = new Admin();
      admin.setName("oussama");
      admin.setUsername("Oussama-kun");
      admin.setEmail("oussama@gmail.com");
      admin.setConnectionDate(new Date(2001-1900,0,13));
      personRepository.save(admin);
      Magasin magasin =new Magasin();
      magasin.setName("Jumia");
      magasin.setId(1L);
      magasinRepository.save(magasin);
      MagasinProduct prod =new MagasinProduct();
      prod.setName("Note11Pro");
      prod.setQuantity(4);
      prod.setMagasin(magasin);
      prod.setPrice(1500.51);
      productRepository.save(prod);

    };
  }
}

