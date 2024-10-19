package com.vsouza.managerproducts.dto.mappers;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.models.ProductRequest;
import com.vsouza.managerproducts.dto.models.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);
    ProductRequest toProductRequest(Product product);
    ProductResponse toProductResponse(Product product);
}
