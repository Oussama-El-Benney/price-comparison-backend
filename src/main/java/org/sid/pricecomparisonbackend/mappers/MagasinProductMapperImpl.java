package org.sid.pricecomparisonbackend.mappers;

import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MagasinProductMapperImpl {
  public MagasinProductDTO fromMagasinProduct(MagasinProduct magasinProduct) {
    MagasinProductDTO magasinProductDTO = new MagasinProductDTO();
    BeanUtils.copyProperties(magasinProduct, magasinProductDTO);
    return magasinProductDTO;
  }


  public MagasinProduct fromMagasinProductDTO(MagasinProductDTO magasinProductDTO) {
    MagasinProduct magasinProduct = new MagasinProduct();
    BeanUtils.copyProperties(magasinProductDTO, magasinProduct);
    return magasinProduct;
  }


}
