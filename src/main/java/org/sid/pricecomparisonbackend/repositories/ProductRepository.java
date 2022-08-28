package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//  List<Product> findByNameContains(String keyword);

//  List<Product> findById(Long id);

  List<Product> findByNameContains(String keyword);


  @Query("select p from Product p where p.name like :kw")
  List<Product> searchProducts(@Param("kw") String keyword);



}
