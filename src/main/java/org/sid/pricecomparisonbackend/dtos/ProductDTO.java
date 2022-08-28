package org.sid.pricecomparisonbackend.dtos;

import lombok.Data;
import org.sid.pricecomparisonbackend.entities.Product;


@Data
public class ProductDTO extends Product {
  private Long id;
  private String name;
  private String description;
  private String imageSrc;
  private int quantity;
  private int rating;
  private double price;


}
