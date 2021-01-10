package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

public class NC242 {
    @Test
    public void Test()
    {
        solution2("a", "b");
    }

    private boolean solution2(String s, String t) {
        if (s ==null && t == null){
            return true;
        }
        else if (s.length()!= t.length()){
            return false;
        }

        int[] d1 = new int['z'-'a'];
        int[] d2 = new int['z'-'a'];
        for (int i = 0; i < s.length()-1; i++) {
            d1[s.charAt(i)-'a'] += 1;
            d2[t.charAt(i)-'a'] += 1;
        }

        for (int i = 0; i < d1.length; i++) {
            if (d1[i] != d2[i]){
                return false;
            }
        }

        return true;
    }
}
