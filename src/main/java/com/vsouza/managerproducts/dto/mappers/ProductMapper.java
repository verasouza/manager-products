package com.vsouza.managerproducts.dto.mappers;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.models.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);
    ProductDTO toProductDTO(Product product);
}
