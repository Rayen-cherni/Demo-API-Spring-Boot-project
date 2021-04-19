package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void calcTest(){
        Calc calc = new Calc();
        Assertions.assertThat(calc.sum(1,2)).isEqualTo(5);
    }

}
