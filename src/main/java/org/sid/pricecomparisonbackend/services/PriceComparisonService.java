package org.sid.pricecomparisonbackend.services;

import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;

public interface PriceComparisonService {

  Person savePerson(Person person, PersonNature nature);
  MagasinProduct saveMagasinProduct(MagasinProduct magasinProduct);
  MagasinProduct getMagasinProduct(Long productId) throws MagasinProductNotFoundException;
}
