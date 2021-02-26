package com.study.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestCollection {

    private static final int[] EMPTY_ELEMENTDATA = {};

    @Test
    public void testList(){
        List<Integer> lst = new ArrayList<>();
        lst.add(lst.size(), 2);
        lst.add(4);
        lst.add(5);
        lst.add(4);

        for (int i = lst.size()-1; i >0 ; i--) {
            lst.remove(i);
        }


        ListIterator<Integer> integerListIterator = lst.listIterator();
        integerListIterator.add(25);
        System.out.println(integerListIterator.next());
//        integerListIterator.add(26);
        System.out.println(integerListIterator.previous());


        ListIterator<Integer> forwardIterator = lst.listIterator();
        ListIterator<Integer> backIterator = lst.listIterator(lst.size());
        while (forwardIterator.hasNext() && backIterator.hasPrevious()){
            if (forwardIterator.nextIndex()==backIterator.previousIndex()){
                System.out.println(forwardIterator.next());
                break;
            }
            System.out.println(forwardIterator.next());
            System.out.println(backIterator.previous());
        }

//        System.out.println(EMPTY_ELEMENTDATA.length);
//        System.out.println(lst.indexOf(4));
//        System.out.println(lst.lastIndexOf(4));

//        ListIterator<Integer> lstIterator = lst.listIterator(lst.size());
//        while(lstIterator.hasPrevious()){
////            System.out.println(lstIterator.previousIndex());
//            Integer value = lstIterator.previous();
//            System.out.println(value);
////            if (value!=null) {
////                lstIterator.set(value + 10);
////            }
//        }

//        ListIterator<Integer> iterator = lst.listIterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

//        lst.forEach(System.out::println);

        System.out.println(lst);
    }
}
