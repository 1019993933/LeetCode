package com.study.offer;

//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
//
public class OF05 {
    public String replaceSpace(String s) {
        return solution1(s);
    }

    private String solution1(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder str = new StringBuilder();

        for (Character c : s.toCharArray()
        ) {
            if (c == ' ') {
                str.append("%20");
            }
            str.append(c);
        }

        return str.toString();
    }

    private String solution2(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder str = new StringBuilder();

        for (Character c : s.toCharArray()
        ) {
            if (c == ' ') {
                str.append("%20");
            }
            str.append(c);
        }

        return str.toString();
    }
}