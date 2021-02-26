package com.study.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<E> extends Collection<E> {
    /**
     * 在末尾添加， 对应List，其实就是添加在index==size()的位置
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 在指定位置添加，位置不能超过size()
     * @param index
     * @param e
     * @return
     */
    boolean add(int index, E e);

    boolean addAll(Collection<? extends E> c);

    boolean addAll(int index, Collection<? extends E> c);

    void clear();

    boolean contains(Object o);

    boolean containsAll(Collection<?> c);

    boolean equals(Object o);

    E get(int index);

    int hashCode();

    boolean isEmpty();

    int indexOf(Object o);

    Iterator<E> iterator();

    int lastIndexOf(Object o);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);


}
