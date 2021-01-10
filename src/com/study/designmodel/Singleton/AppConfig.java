package com.study.designmodel.Singleton;

public class AppConfig {

    /**
     * 单例实例对象，由于是静态变量，在类加载的时候初始化，根据是否初始化值来确定是懒汉模式还是饿汉式
     * 如果在定义的时候就初始化，则属于饿汉式
     * 如果在调用的时候初始化，属于懒汉式
     *
     */
    private static AppConfig mInstance;


    /**
     * 单例模式的构造函数必须私有，避免外部调用，只有一个getInstance()接口是公布给外部的
     */
    private AppConfig(){

    }


    private int data;

    //懒汉式，由于在调用的时候才初始化，所以需要加线程同步机制
    //1，对方法进行同步，性能影响大
    synchronized public static AppConfig getInstance(){
        if (mInstance==null){
            mInstance = new AppConfig();
        }
        return mInstance;
    }
    
    public static AppConfig getInstance2(){
        if (mInstance == null) {
            synchronized (mInstance) {
                if (mInstance == null) {
                    mInstance = new AppConfig();
                }
            }
        }
        return mInstance;
    }
}
