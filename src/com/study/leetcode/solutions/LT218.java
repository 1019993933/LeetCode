package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.*;

public class LT218 {
    public class Point implements Comparable<Point>{
        Integer x;
        int height;
        boolean isLeft;
        Point pariPoint;

        public Point(int x, int height, boolean isLeft,Point pariPoint){
            this.x = x;
            this.height = height;
            this.isLeft = isLeft;
            this.pariPoint = pariPoint;
        }

        public List<Integer> getList(int height){
            List<Integer> lst = new ArrayList<>();
            lst.add(x);
            lst.add(height);
            return lst;
        }
        @Override
        public int compareTo(Point o) {
            if (this.isLeft==o.isLeft && this.x==o.x && this.height==o.height){
                return 0;
            }

            if (this.x!=o.x){
                return this.x-o.x;
            }

            if (this.isLeft!=o.isLeft){
                return this.isLeft? 1: -1;
            }

           return this.height-o.height;
        }

        public int heightCompareTo(Point o){
            return this.height - o.height;
        }
    }

    @Test
    public void dotest(){
        TreeSet<Point> set = new TreeSet<>();
        Point left = new Point(2, 3, true, null);
        Point right = new Point(2, 4, true, null);
        System.out.println(left.hashCode());
        System.out.println(right.hashCode());
        System.out.println(left.equals(right));
        set.add(left);
        set.add(right);
        set.remove(left);

        System.out.println("aa");
    }
}
