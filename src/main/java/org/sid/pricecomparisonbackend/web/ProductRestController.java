package org.sid.pricecomparisonbackend.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;
import org.sid.pricecomparisonbackend.services.PriceComparisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ProductRestController {
private PriceComparisonService priceComparisonService;

  @GetMapping("/products")
  public List<MagasinProductDTO> products(){
    return priceComparisonService.magasinProductList();
  }


  @GetMapping("/products/{id}")
  public MagasinProductDTO getMagasinProduct(@PathVariable(name = "id") Long productId) throws MagasinProductNotFoundException {
    return priceComparisonService.getMagasinProductDTO(productId);
  }
  @GetMapping("/products/search")
  public List<ProductDTO> searchProducts(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    return priceComparisonService.searchProducts("%"+keyword+"%");
  }

  @GetMapping("/products/searchs")
  public List<ProductDTO> searchProductsById(@RequestParam(name = "id",defaultValue = "") Long id){
    return priceComparisonService.searchProductsById(id);
  }

  @PostMapping("/products")
  public MagasinProductDTO saveProduct(@RequestBody MagasinProductDTO magasinProductDTO){
    return priceComparisonService.saveMagasinProduct(magasinProductDTO);
  }


}


