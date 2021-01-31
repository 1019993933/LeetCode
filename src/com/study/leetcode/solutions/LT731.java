package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class LT731 {
    private TreeSet<Range> ranges = new TreeSet<>();

    public LT731() {

    }

    @Test
    public void test() {
        LT731 obj = new LT731();
        boolean book = obj.book(24, 40); //true
        book = obj.book(43, 50); //true
        book = obj.book(27, 43); //true
        book = obj.book(5, 21); //true
        book = obj.book(30, 40);//FALSE
        book = obj.book(14, 29);//FALSE
        book = obj.book(3, 19);//true
        book = obj.book(3, 14);//FALSE
        book = obj.book(25, 39);//FALSE
        book = obj.book(6, 19);//FALSE

        System.out.println(book);
    }


    public boolean book(int start, int end) {
        Iterator<Range> ite = ranges.tailSet(new Range(0, start+1, 0)).iterator();

        List<Range> lst = new ArrayList<>();
        List<Range> backupLst = new ArrayList<>();
        while (ite.hasNext()) {
            Range range = ite.next();
            if (range.left > end) {
                break;
            }
            if (start >= end) {
                break;
            }

            if (range.val >= 2 && (Math.max(range.left, start)<Math.min(range.right, end))){
                ranges.addAll(backupLst);
                return false;
            }
            lst.add(new Range(Math.min(range.left, start), Math.max(range.left, start), 1));
            lst.add(new Range(Math.max(range.left, start), Math.min(range.right, end), 2));
            start = Math.min(range.right, end);
            if (start==end && start<range.right){
                range.left = start;
            }
            else {
                backupLst.add(range);
                ite.remove();
            }
        }

        if (start < end) {
            lst.add(new Range(start, end, 1));
        }

        for (Range range : lst) {
            if (range.left < range.right) {
                ranges.add(range);
            }
        }

        //TODO:
        return true;
    }

    class Range implements Comparable<Range> {
        private int left;
        private int right;
        private int val;

        public Range(int left, int right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        @Override
        public int compareTo(Range other) {
            return this.right == other.right ? this.left - other.left : this.right - other.right;
        }
    }
}
