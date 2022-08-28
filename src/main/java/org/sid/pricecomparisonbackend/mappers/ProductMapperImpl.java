package org.sid.pricecomparisonbackend.mappers;

import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl {
  public ProductDTO fromProduct(Product Product) {
    ProductDTO ProductDTO = new ProductDTO();
    BeanUtils.copyProperties(Product, ProductDTO);
    return ProductDTO;
  }


  public Product fromProductDTO(ProductDTO ProductDTO) {
    Product Product = new Product();
    BeanUtils.copyProperties(ProductDTO, Product);
    return Product;
  }


}
