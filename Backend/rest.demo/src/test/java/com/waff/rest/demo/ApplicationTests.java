package com.waff.rest.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyServiceTests {

    @Test
    void testAddition() {
        MyService myService = new MyService();
        assertEquals(5, myService.addition(2, 3), "2 + 3 should equal 5");
    }
}