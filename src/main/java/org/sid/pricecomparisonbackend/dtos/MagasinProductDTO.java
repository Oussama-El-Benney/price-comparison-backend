package org.sid.pricecomparisonbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.pricecomparisonbackend.entities.Magasin;
import org.sid.pricecomparisonbackend.entities.Product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Data
public class MagasinProductDTO extends Product {
  private Long id;
  private String name;
  private String description;
  private int quantity;
  private double price;


}
