package com.vsouza.managerproducts.service;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.mappers.ProductMapper;
import com.vsouza.managerproducts.dto.models.ProductDTO;
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

    public Set<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductDTO).collect(Collectors.toSet());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return productMapper.toProductDTO(product);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
