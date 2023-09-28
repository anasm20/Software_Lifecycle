package com.waff.rest.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.waff.rest.demo.repository.ProductRepository;
import com.waff.rest.demo.model.Product;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StorageService storageService;

    @BeforeEach
    public void setUp() {
        // productService = new ProductService(productRepository, storageService);
        productService = new ProductService(storageService, productRepository);
    }

    @Test
    public void shouldReturnAllFilteredProducts() {
        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(
                new Product());
        dummyProducts.add(new Product());

        when(productRepository.findAll()).thenReturn(dummyProducts);

        List<Product> result = productService.getProducts();

        assertEquals(dummyProducts, result);

        verify(productRepository, times(1)).findAll();
    }

}
