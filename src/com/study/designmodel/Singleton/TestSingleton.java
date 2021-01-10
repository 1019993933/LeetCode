package com.study.designmodel.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

public class TestSingleton {
    @Test
    public void test() {
        Singleton.getInstance().test();

        AppConfig.getInstance();
        AppConfig.getInstance2();

        SingletonByEnum.mInstance.test();
        SingletonByEnum.mInstance.getData();
    }

    @Test
    public void refectAppConfig() throws Exception {
        System.out.println(AppConfig.getInstance());

        Field file = AppConfig.class.getDeclaredField("mInstance");
        file.setAccessible(true);
        Constructor<?> constructor = AppConfig.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();
        file.set(AppConfig.class, o);
        System.out.println(AppConfig.getInstance());
    }

    /**
     * 反射破坏单例模式
     *
     * @throws Exception
     */
    @Test
    public void refectSingleton() throws Exception {
        System.out.println(Singleton.getInstance());

        //获取内部类的静态成员属性
        Class singletonHolderClass = Class.forName("com.study.designmodel.Singleton.Singleton$SingletonHolder");
        Field file = singletonHolderClass.getDeclaredField("mInstance");
        file.setAccessible(true);

        //获取外部类的私有构造方法，生成新实例
        Constructor<?> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();

        file.set(Singleton.class, o);
        System.out.println(Singleton.getInstance());
    }
}
