package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Magasin;
import org.sid.pricecomparisonbackend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasinRepository extends JpaRepository<Magasin,Long> {
}
