package org.sid.pricecomparisonbackend.services;

import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.enums.PersonNature;

public interface PriceComparisonService {

  Person savePerson(Person person, PersonNature nature);
}
