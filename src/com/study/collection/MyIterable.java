package com.study.collection;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface MyIterable<T> {
    Iterable<T> iterator();

    default void forEach(Consumer<? super T> action){
        Objects.requireNonNull(action);
        for (T t: iterator()){
            action.accept(t);
        }
    }

    default Spliterator<T> sipliterator(){
        return null;
    }
}
