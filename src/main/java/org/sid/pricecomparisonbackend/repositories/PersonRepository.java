package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
