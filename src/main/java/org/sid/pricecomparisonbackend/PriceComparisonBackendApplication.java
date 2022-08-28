package org.sid.pricecomparisonbackend;

import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.entities.*;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.mappers.ProductMapperImpl;
import org.sid.pricecomparisonbackend.repositories.*;
import org.sid.pricecomparisonbackend.services.PriceComparisonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class PriceComparisonBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceComparisonBackendApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  CommandLineRunner start(PriceComparisonService priceComparisonService,
                          MagasinRepository magasinRepository,
                          ProductRepository productRepository,
                          FavoritesRepository favoritesRepository,
                          PersonRepository personRepository,
                          ProductMapperImpl productMapper,
                          AccountService accountService) {
    return args -> {
//authetication
//      accountService.addNewRole(new AppRole(2, "CLIENT"));
//      accountService.addNewRole(new AppRole(1, "ADMIN"));
//      accountService.addNewUser(new AppUser(6, "user1", "123", new ArrayList<>()));
//      accountService.addNewUser(new AppUser(7,"user1","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(8,"user2","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(9,"user3","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(10,"user4","123",new ArrayList<>()));
//      accountService.addRoleToUser("user3","USER");
//      accountService.addRoleToUser("user4","ADMIN");



      accountService.addNewUser(new AppUser(0, "user37", "123", new ArrayList<>()));




      //db completion
      Stream.of("Sirat", "Oussama", "Mohammed").forEach(name -> {
        Client customer = new Client();
        customer.setNature(PersonNature.CLIENT);
        customer.setName(name);
        customer.setUsername(name + "_kun");
        customer.setEmail(name + "@gmail.com");
        personRepository.save(customer);

      });


      Client client = new Client();
      client.setNature(PersonNature.CLIENT);
      client.setName("ohsama");
      client.setUsername("ohsama" + "_kun");
      client.setEmail("ohsama" + "@gmail.com");
      client.setAdresse("maamoura");
      client.setTelNumber(29004323);


      Favorites favorites = new Favorites();
      favorites.setId(2L);
//      favorites.setClient(personRepository.findByName("oussama"));
      favorites.setName("oussa favorites");
      client.setFavorites(favorites);
      favorites.setClient(client);

      Magasin magasin = new Magasin();
      magasin.setName("myTek");
      magasin.setId(2L);
      magasinRepository.save(magasin);


      MagasinProduct prod = new MagasinProduct();
      prod.setName("Iphone 11 Pro max plus");
      prod.setQuantity(5);
      prod.setMagasin(magasin);
      prod.setRating(4);
      prod.setPrice(1500.51);
      prod.setImageSrc("https://thumbs.dreamstime.com/b/new-iphone-features-all-screen-design-99917162.jpg");
      productRepository.save(prod);


      MagasinProduct produ = new MagasinProduct();
      produ.setName("nokia 3310");
      produ.setQuantity(3);
      produ.setRating(5);
      produ.setMagasin(magasin);
      produ.setPrice(150.2);
      produ.setImageSrc("https://upload.wikimedia.org/wikipedia/commons/7/78/Nokia_3310_Blue_R7309170_%28retouch%29.png");
      productRepository.save(produ);

      List<Product> productList = new ArrayList<Product>();
      ;
      productList.add(produ);
      productList.add(prod);
      favorites.setFavoriteProducts(productList);
      personRepository.save(client);
      favoritesRepository.save(favorites);
//      priceComparisonService.magasinProductList().forEach(magasinProduct -> {
//
//      });
    };
  }

  //  @Bean
  CommandLineRunner start(PersonRepository personRepository,
                          MagasinProductRepository magasinProductRepository,
                          ProductRepository productRepository,
                          MagasinRepository magasinRepository) {
    return args -> {
      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
        Client customer = new Client();
        customer.setNature(PersonNature.CLIENT);
        customer.setName(name);
        customer.setUsername(name + "_kun");
        customer.setEmail(name + "@gmail.com");
        personRepository.save(customer);
      });

      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
        Client client = new Client();
        client.setNature(PersonNature.CLIENT);
        client.setName(name);
        client.setUsername(name + "-kun");
        client.setEmail(name + "@gmail.com");
        client.setTelNumber(29004323);
        personRepository.save(client);
      });

      Admin admin = new Admin();
      admin.setNature(PersonNature.ADMIN);
      admin.setName("oussama");
      admin.setUsername("Oussama-kun");
      admin.setEmail("oussama@gmail.com");
      admin.setConnectionDate(new Date(2001 - 1900, 0, 13));
      personRepository.save(admin);

      Magasin magasin = new Magasin();
      magasin.setName("Jumia");
      magasin.setId(1L);
      magasinRepository.save(magasin);

      personRepository.findByNature(PersonNature.CLIENT).forEach(client -> {
        log.info(client.getName());
        if (Objects.equals(client.getName(), "Sirat")) {
          client.setAdresse("bousalem");
        } else {
          client.setAdresse("maamoura");
        }

        log.info(client.getAdresse());
        personRepository.save(client);
      });

    };
  }
}

