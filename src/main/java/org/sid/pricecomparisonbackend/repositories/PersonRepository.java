package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Client;
import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.entities.Product;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
  public List<Client> findByNature(PersonNature n);
  public Client findByName(String n);
}
