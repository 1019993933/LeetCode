package com.study.gc;

import org.junit.jupiter.api.Test;

public class GCTest {
    private static final int _1M = 1024*1024;

    @Test
    public void test(){
        testAllocation();
    }

    public void testAllocation(){
        byte[] a1, a2, a3, a4;
        a1 = new byte[2*_1M];
        a2 = new byte[2*_1M];
        a3 = new byte[2*_1M];
        a4 = new byte[4*_1M];
    }
}
