package com.study.collection;

import java.util.Objects;
import java.util.function.Consumer;

public interface MyIterator<T> {
    boolean hasNext();

    T next();

    default void remove(){
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super T> action){
        Objects.requireNonNull(action);
        while (hasNext()){
            action.accept(next());
        }
    }
}
