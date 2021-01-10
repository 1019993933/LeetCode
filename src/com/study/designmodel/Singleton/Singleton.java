package com.study.designmodel.Singleton;

public class Singleton {
    private static class SingletonHolder {
        private static Singleton mInstance = new Singleton();
    }

    private Singleton() {

    }

    public void test() {
        System.out.println("This is an test from Singleton_Model!");
    }

    public static Singleton getInstance() {
        return SingletonHolder.mInstance;
    }
}
