package com.vsouza.managerproducts;

import com.vsouza.managerproducts.dto.entities.Product;
import com.vsouza.managerproducts.dto.mappers.ProductMapper;
import com.vsouza.managerproducts.dto.models.ProductDTO;
import com.vsouza.managerproducts.repositories.ProductRepository;
import com.vsouza.managerproducts.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct_shouldReturnSavedProduct(){
        Product productRequest = new Product();
        productRequest.setName("Ipad");
        productRequest.setPrice(5.000);
        productRequest.setDescription("Tablet da Apple");

        Product productResponse = new Product();
        productResponse.setName("Ipad");
        productResponse.setPrice(5.000);
        productResponse.setDescription("Tablet da Apple");

        ProductDTO request = getProductDTO(productRequest);
        ProductDTO response = getProductDTO(productResponse);

        when(productMapper.toProduct(request)).thenReturn(productRequest);
        when(productRepository.save(productRequest)).thenReturn(productResponse);
        when(productMapper.toProductDTO(productResponse)).thenReturn(response);


        ProductDTO savedProduct = productService.addProduct(request);

        assertEquals(productRequest.getId(), savedProduct.getId());
        assertEquals(productRequest.getName(), savedProduct.getName());
        assertEquals(productRequest.getPrice(), savedProduct.getPrice());

    }

    @Test
    void getProductById_shouldReturnProductWithId(){
        Product productRequest = new Product();
        productRequest.setId(1L);
        productRequest.setName("Ipad");
        productRequest.setPrice(5.000);
        productRequest.setDescription("Tablet da Apple");

        ProductDTO productResponse = getProductDTO(productRequest);

        when(productRepository.findById(productRequest.getId())).thenReturn(Optional.of(productRequest));
        when(productMapper.toProductDTO(productRequest)).thenReturn(productResponse);

        ProductDTO productById = productService.getProductById(productRequest.getId());
        assertEquals(productRequest.getId(), productById.getId());
        assertEquals(productRequest.getName(), productById.getName());
        assertEquals(productRequest.getPrice(), productById.getPrice());

    }

    @Test
    void getProductByNonExistId_shouldNotReturnProductWithId(){
        Long nonExistId = 1L;
        when(productRepository.findById(nonExistId)).thenReturn(Optional.empty());
        ProductDTO productById = productService.getProductById(nonExistId);
        assertNull(productById);

    }

    @Test
    void getAllProducts_shouldReturnAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Ipad", "Tablet da Apple", 5.000));
        productList.add(new Product(2L, "TV", "TV QLED 50 polegadas", 5.000));

        Set<ProductDTO> productListDTO = new HashSet<>();
        productListDTO.add(new ProductDTO(1L, "Ipad", "Tablet da Apple", 5.000));
        productListDTO.add(new ProductDTO(2L, "TV", "TV QLED 50 polegadas", 5.000));

        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toProductDTO(any(Product.class))).thenAnswer(invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0);
            return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
        });

        Set<ProductDTO> allProducts = productService.getAllProducts();
        assertEquals(productListDTO.size(), allProducts.size());
        assertTrue(CollectionUtils.isEqualCollection(productListDTO, allProducts));

    }

    @Test
    void deleteProduct_shouldDeleteProductInRepository() {
        Long productId = 1L;

        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);

    }

    ProductDTO getProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }



}
