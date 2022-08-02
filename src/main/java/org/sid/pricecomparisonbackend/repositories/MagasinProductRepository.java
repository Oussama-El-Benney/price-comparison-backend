package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Magasin;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasinProductRepository extends JpaRepository<MagasinProduct, Long> {
}
