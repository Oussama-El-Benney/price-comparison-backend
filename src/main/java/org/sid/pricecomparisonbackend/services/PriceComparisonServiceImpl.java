package org.sid.pricecomparisonbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.entities.Admin;
import org.sid.pricecomparisonbackend.entities.Client;
import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PriceComparisonServiceImpl implements PriceComparisonService {
  private PersonRepository personRepository;


  @Override
  public Person savePerson(Person person, PersonNature nature) {

    log.info("Saving new Person");
//    Person savedPerson = personRepository.save(person);
    Person savedPerson;
    if(nature==PersonNature.CLIENT){
      savedPerson= new Client();
    } else {
      savedPerson= new Admin();
    }

    return savedPerson;
  }
}
