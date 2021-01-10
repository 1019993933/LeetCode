package com.study.leetcode.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    @Test
    public void testToArray(){
        String str = "[19,24,8]";
        int[] ints = toArray(str);
        ;

        String send = "[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]";
        int[][] data = toTwoDimArray(send);

        System.out.println();
    }


    /**
     * 将力扣中的输入数组转换为 数组形式
     * [1,23,3443,1,213,21]
     */
    public static int[] toArray(String str){
        List<Integer> lst = new ArrayList<>();

        if (str.charAt(0)=='['){
            str = str.substring(1, str.length());
        }
        if (str.charAt(str.length()-1)==']'){
            str = str.substring(0, str.length()-1);
        }
        String[] split = str.split(",");
        for (String s : split) {
            lst.add(Integer.valueOf(s));
        }

        return lst.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 转换为二维数组Two-dimensional array
     * [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     */
    public static int[][] toTwoDimArray(String str)
    {
        String subStr = str.substring(1, str.length()-1);//去掉第一层[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]
        String[] split = subStr.split("],");
        int n = split.length;

        int[][] ans = new int[n][];

        for (int i = 0; i < split.length; i++) {
            ans[i] = toArray(split[i]);
        }
        return ans;
    }

    private static String trimHeadSeperator(String target){
        if (target.charAt(0)=='['){
            return target.substring(1);
        }

        return target;
    }

    /**
     * 判断是否是数组， [开头，]结尾
     * @param target 
     * @return
     */
    private static boolean isArrayStr(String target) {
        return (target != null && target.length() >= 2 && target.charAt(0) == '['
            && target.charAt(target.length() - 1) == ']');
    }
}
