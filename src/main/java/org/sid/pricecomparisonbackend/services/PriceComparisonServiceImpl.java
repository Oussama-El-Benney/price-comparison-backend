package org.sid.pricecomparisonbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.entities.Admin;
import org.sid.pricecomparisonbackend.entities.Client;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;
import org.sid.pricecomparisonbackend.repositories.MagasinProductRepository;
import org.sid.pricecomparisonbackend.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PriceComparisonServiceImpl implements PriceComparisonService {
  private PersonRepository personRepository;
  private MagasinProductRepository magasinProductRepository;

  @Override
  public Person savePerson(Person person, PersonNature nature) {

    log.info("Saving new Person");
//    Person savedPerson = personRepository.save(person);

    if (nature == PersonNature.CLIENT) {
      person = new Client();
    } else {
      person = new Admin();
    }
    Person savedPerson = personRepository.save(person);
    return savedPerson;
  }

  @Override
  public MagasinProduct saveMagasinProduct(MagasinProduct magasinProduct) {
    log.info("Saving new Product");
    MagasinProduct savedMagasinProduct = magasinProductRepository.save(magasinProduct);
    return savedMagasinProduct;
  }

  @Override
  public MagasinProduct getMagasinProduct(Long productId) throws MagasinProductNotFoundException {
    MagasinProduct magasinProduct = magasinProductRepository.findById(productId)
            .orElseThrow(() -> new MagasinProductNotFoundException("BankAccount not found"));
    return magasinProduct;
  }




}
