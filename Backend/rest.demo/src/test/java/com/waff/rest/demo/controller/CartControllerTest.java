package com.waff.rest.demo.controller;

import com.waff.rest.demo.model.Cart;
import com.waff.rest.demo.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    private CartController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CartController(cartService);
    }

    @Test
    void getCartsTest() {
        when(cartService.getCarts()).thenReturn(Collections.singletonList(new Cart()));
        ResponseEntity<List<Cart>> response = controller.getCarts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findCartByIdTest() {
        when(cartService.getCartById(anyString())).thenReturn(Optional.of(new Cart()));
        ResponseEntity<Cart> response = controller.findCartById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getCartByUserIdTest() {
        when(cartService.getCartByUserId(anyString())).thenReturn(Optional.of(new Cart()));
        ResponseEntity<Cart> response = controller.getCartByUserId("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addProductToCartTest() {
        when(cartService.addItemToCart(anyString(), anyString())).thenReturn(Optional.of(new Cart()));
        ResponseEntity<Cart> response = controller.addProductToCart("1", "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeProductToCartTest() {
        when(cartService.removeItemToCart(anyString(), anyString())).thenReturn(Optional.of(new Cart()));
        ResponseEntity<Cart> response = controller.removeProductToCart("1", "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void clearCartTest() {
        when(cartService.clearCart(anyString())).thenReturn(Optional.of(new Cart()));
        ResponseEntity<Cart> response = controller.clearCart("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
