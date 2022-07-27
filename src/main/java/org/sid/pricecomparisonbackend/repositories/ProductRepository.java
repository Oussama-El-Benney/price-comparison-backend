package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
