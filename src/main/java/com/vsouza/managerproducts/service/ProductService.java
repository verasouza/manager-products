package com.vsouza.managerproducts.service;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.mappers.ProductMapper;
import com.vsouza.managerproducts.dto.models.ProductDTO;
import com.vsouza.managerproducts.queues.MessageFacade;
import com.vsouza.managerproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MessageFacade messageFacade;

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
        product.setCreatedAt(LocalDate.now());
        product.setUpdatedAt(LocalDate.now());
        productRepository.save(product);
        messageFacade.sendMessage("Product " + product.getName() + " added successfully");
        return productMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setUpdatedAt(LocalDate.now());
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
