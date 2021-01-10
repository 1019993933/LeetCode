package com.study.lambdaStudy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public class LambaTest {
    @FunctionalInterface
    public interface Upper<T>{
        void upper(T t);
    }
    public static void main(String [] args){
        IntBinaryOperator binaryOperator = (int a, int b) -> {
            return a + b;
        };

        int result = binaryOperator.applyAsInt(1,2);
        System.out.println(result);


        Thread threa = new Thread(()->{
            System.out.println("This run on thread.");
        });
        threa.start();


        new Thread(()->{System.out.println("This run on thread.2");}).start();

        try {
            threa.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<Integer> lst = Arrays.asList(12, 3, 3);
        lst.forEach(System.out::println);

        Consumer<String> tool = (a)->a.toUpperCase();

        Function<String, String> mapper = a->a.toUpperCase();

        int[] data = new int[]{23,324231,123};
        List<String> lst2 = Arrays.asList("fda", "afdad", "243");
        lst2.sort(String::compareTo);
        lst2.stream().map(a->a.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
        lst2.forEach(System.out::println);

        System.out.println("fda".toUpperCase());
    }
}
