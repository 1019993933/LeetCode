package com.study.designmodel.Singleton;

public enum SingletonByEnum{
    mInstance;//枚举类型对应的实例数据与方法定义必须在此分号后面

    private int data;

    public void test(){
        System.out.println("this is a singleton waht implementing with enum");
    }

    public int getData(){
        return this.data;
    }

    /**
     * 枚举类型的构造方法必须私有
     */
    private SingletonByEnum(){

    }
}
