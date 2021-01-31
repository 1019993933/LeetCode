package com.study.designmodel.builder.creator;

import org.junit.jupiter.api.Test;

public class Client {
    @Test
    public void doTest(){
        Student stu1 = new Student.Builder().setName("张三").setAge(29).setMale(true).build();
        System.out.println(stu1.toString());


        Student stu2 = new Student.Builder().setName("李四").setMale(false).build();
        System.out.println(stu2.toString());
    }
}
