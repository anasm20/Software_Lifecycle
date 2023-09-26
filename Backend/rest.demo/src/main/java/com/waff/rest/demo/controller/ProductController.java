package com.waff.rest.demo.controller;

import com.waff.rest.demo.dto.ProductDto;
import com.waff.rest.demo.model.Category;
import com.waff.rest.demo.model.Product;
import com.waff.rest.demo.model.ProductFilter;
import com.waff.rest.demo.service.CategoryService;
import com.waff.rest.demo.service.ProductService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/product/search")
    public ResponseEntity<List<Product>> getProductsByFilter(@Valid @RequestBody ProductFilter filter) {
        return ResponseEntity.ok(productService.searchByFilter(filter));
    }


    @PostMapping(value = "/admin/product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Product> createCategory(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
        Product product = modelMapper.map(productDto, Product.class);
        if(StringUtils.isNotBlank(productDto.getCategoryId())) {
            product.setCategory(new Category().setId(productDto.getCategoryId()));
        }
        Product created = productService.createProduct(product, productDto.getImage()).orElse(null);
        if(created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id).orElse(null);
        if(product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/admin/product/{id}")
    public ResponseEntity<Product> updateProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updated = productService.updateProduct(product, productDto.getImage()).orElse(null);
        if(updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        if(productService.deleteProductById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/product/")
    public ResponseEntity<Void> deleteProducts() {
        productService.deleteProducts();
        return ResponseEntity.ok().build();
    }
}
