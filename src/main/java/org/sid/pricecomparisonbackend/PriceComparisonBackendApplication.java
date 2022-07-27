package org.sid.pricecomparisonbackend;

import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class PriceComparisonBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceComparisonBackendApplication.class, args);
  }
  @Bean
  CommandLineRunner start(PersonRepository personRepository){
    return args -> {
      Stream.of("Hassan","Imane","Mohamed").forEach(name->{
        Person customer=new Person();
        customer.setName(name);
        customer.setEmail(name+"@gmail.com");
        personRepository.save(customer);
      });
    };
  }
}

