package com.waff.rest.demo.controller;

import com.waff.rest.demo.dto.CategoryDto;
import com.waff.rest.demo.model.Category;
import com.waff.rest.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;
    
    @Mock
    private ModelMapper modelMapper;

    private CategoryController controller;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CategoryController(categoryService, modelMapper);
    }

    @Test
    void createCategoryTest() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Electronics");
        categoryDto.setImage(new MockMultipartFile("file", "originalFilename", "text/plain", "data".getBytes()));
        
        Category categoryMock = mock(Category.class);
        
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(categoryMock);
        when(categoryService.createCategory(any(Category.class), any())).thenReturn(java.util.Optional.of(categoryMock));
        
        ResponseEntity<Category> response = controller.createCategory(categoryDto, null);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
