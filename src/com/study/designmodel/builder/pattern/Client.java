package com.study.designmodel.builder.pattern;

import org.junit.jupiter.api.Test;

public class Client {
    @Test
    public void doTest(){
        Director director1 = new Director(1);
        System.out.println(director1.getProduct());

        Director director2 = new Director(2);
        System.out.println(director2.getProduct());
    }
}
