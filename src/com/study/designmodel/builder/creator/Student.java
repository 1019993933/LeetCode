package com.study.designmodel.builder.creator;

/**
 * 构建者通常都实现在需要生成的产品内部类
 */
public class Student {
    private String name;
    private int age;
    private boolean male;


    /**
     * 本身构建函数不对外暴露，只通过Builder来生成
     * 并用builder对象作为参数来构建实际对象
     */
    private Student(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.male = builder.male;
    }

    public String toString(){
        return String.format("学生姓名：%s, 年龄: %d, 性别: %s", this.name, this.age, this.male? "男":"女");
    }

    /**
     * 构建者通常具有与实际产品完全相同的属性，以实现参数的传递
     */
    static class Builder{
        private String name;
        private int age;
        private boolean male;

        /**
         * 每一个设置方法返回Builder本身，用来实现链式编程
         * @param name
         * @return
         */
        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setMale(boolean isMale){
            this.male = isMale;
            return this;
        }

        /**
         * 可以提供一些初始化，或者默认值等
         */
        public Builder(){
            this.age = 18;
            this.male = true;
        }

        /**
         * 返回真正的产品对象，也可以叫Create等名称
         * @return
         */
        public Student build(){
            return new Student(this);
        }
    }
}
