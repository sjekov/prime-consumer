package com.offerista.consumer.primeconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
class PrimeNumberConsumerTest {
    @Autowired
    PrimeNumberConsumer primeNumberConsumer;
    private Boolean[] primesfrom2to19 = new Boolean[]{true, true, false, true, false, true, false, false
            , false, true, false, true, false, false, false, true, false, true};

    @BeforeEach
    void setUp() {

    }


    @Test
    void primeNumberChecker() {
        List<Boolean> result = new ArrayList<>();
        for (int i = 2; i < 20; i++) {
            boolean b = primeNumberConsumer.isPrimeNumber(i);
            result.add(b);
        }
        assertArrayEquals(primesfrom2to19, result.toArray());
    }
}