package org.sid.pricecomparisonbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.*;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;
import org.sid.pricecomparisonbackend.mappers.MagasinProductMapperImpl;
import org.sid.pricecomparisonbackend.mappers.ProductMapperImpl;
import org.sid.pricecomparisonbackend.repositories.MagasinProductRepository;
import org.sid.pricecomparisonbackend.repositories.PersonRepository;
import org.sid.pricecomparisonbackend.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PriceComparisonServiceImpl implements PriceComparisonService {
  private PersonRepository personRepository;
  private MagasinProductRepository magasinProductRepository;
  private ProductRepository productRepository;
  private MagasinProductMapperImpl magasinProductMapper;
  private ProductMapperImpl productMapper;

  @Override
  public Person savePerson(Person person, PersonNature nature) {

    log.info("Saving new Person");
//    Person savedPerson = personRepository.save(person);

    if (nature == PersonNature.CLIENT) {
      person = new Client();
    } else {
      person = new Admin();
    }
    Person savedPerson = personRepository.save(person);
    return savedPerson;
  }

  @Override
  public MagasinProductDTO saveMagasinProduct(MagasinProductDTO magasinProductDTO) {
    log.info("Saving new Product");
    MagasinProduct magasinProduct = magasinProductMapper.fromMagasinProductDTO(magasinProductDTO);
    MagasinProduct savedMagasinProduct = magasinProductRepository.save(magasinProduct);
    return magasinProductMapper.fromMagasinProduct(savedMagasinProduct);
  }

  @Override
  public MagasinProduct getMagasinProduct(Long productId) throws MagasinProductNotFoundException {
    MagasinProduct magasinProduct = magasinProductRepository.findById(productId)
            .orElseThrow(() -> new MagasinProductNotFoundException("Product not found"));
    return magasinProduct;
  }

  @Override
  public MagasinProductDTO getMagasinProductDTO(Long productId) throws MagasinProductNotFoundException {
    MagasinProduct magasinProduct = magasinProductRepository.findById(productId)
            .orElseThrow(() -> new MagasinProductNotFoundException("Product not found"));
    MagasinProductDTO magasinProductDTO = magasinProductMapper.fromMagasinProduct(magasinProduct);
    return magasinProductDTO;
  }

  @Override
  public List<MagasinProductDTO> magasinProductList() {
    List<MagasinProduct> magasinProducts = magasinProductRepository.findAll();

    List<MagasinProductDTO> magasinProductDTOS = magasinProducts.stream().map(prod ->
              magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());

    return magasinProductDTOS;
  }

  @Override
  public List<ProductDTO> searchProducts(String keyword) {
        List<Product> products=productRepository.searchProducts(keyword);
//    List<Product> product=productRepository.findByNameContains(keyword);
//
////    List<MagasinProductDTO> MagasinProductDTOS = products.stream().map(prod -> magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());
    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());
//
    return ProductDTOS;
//    return null;
  }

  public List<ProductDTO> searchProductsById(Long id) {
//        List<Product> products=productRepository.findByNameContains(keyword);

    Optional<Product> products=productRepository.findById(id);
//    List<MagasinProductDTO> MagasinProductDTOS = products.stream().map(prod -> magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());
    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());

    return ProductDTOS;
//    return null;
  }
}
