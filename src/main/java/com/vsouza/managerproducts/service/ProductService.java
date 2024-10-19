package com.vsouza.managerproducts.service;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.mappers.ProductMapper;
import com.vsouza.managerproducts.dto.models.ProductRequest;
import com.vsouza.managerproducts.dto.models.ProductResponse;
import com.vsouza.managerproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Set<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).collect(Collectors.toSet());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return productMapper.toProductResponse(product);
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
