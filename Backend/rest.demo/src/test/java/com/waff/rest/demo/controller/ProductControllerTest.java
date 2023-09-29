package com.waff.rest.demo.controller;

import com.waff.rest.demo.dto.ProductDto;
import com.waff.rest.demo.model.Category;
import com.waff.rest.demo.model.Product;
import com.waff.rest.demo.service.CategoryService;
import com.waff.rest.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    
    @Mock
    private CategoryService categoryService;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private ProductController productController;

    @Test
    void testGetProducts() {
        Product product = new Product();
        List<Product> products = Arrays.asList(product);
        when(productService.getProducts()).thenReturn(products);
        ResponseEntity<List<Product>> response = productController.getProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        Category category = new Category();
        product.setCategory(category);
        MockMultipartFile file = new MockMultipartFile("image", "originalfilename.jpg", "image/jpeg", "some-image".getBytes());
        productDto.setImage(file);
        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(productService.createProduct(product, file)).thenReturn(java.util.Optional.of(product));
        ResponseEntity<Product> response = productController.createCategory(productDto, null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }
}

