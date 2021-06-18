package com.tenpo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

    @Autowired
    private OperationService operationService;

    @Test
    @DisplayName("Testing multiply operations from math service")
    public void testMultiplyOperations() {
        assertEquals(2.0, (operationService.multiply(2.0, 1.0)), 0);
        assertEquals(6.0, (operationService.multiply(3.0, 2.0)), 0);
        assertEquals(20.0, (operationService.multiply(2.0, 10.0)), 0);
        assertEquals(-20.0, (operationService.multiply(10.0, -2.0)), 0);
    }
}
