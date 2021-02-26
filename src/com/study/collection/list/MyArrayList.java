package com.study.collection.list;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class MyArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    public MyArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int minCapacity){
        if (minCapacity==0){
            this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        } else if (minCapacity>0){
            this.elementData = new Object[minCapacity];
        } else{
            throw new IllegalArgumentException("eee");
        }
    }

    public MyArrayList(Collection<? extends E> c){
        this.elementData = c.toArray();
    }



    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
