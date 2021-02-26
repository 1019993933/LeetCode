package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LK846 {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        if (W == 1) {
            return true;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < hand.length; i++) {
            int key = hand[i];
            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
        }

        for (int i = 0; i < hand.length / W; i++) {
            Map.Entry<Integer, Integer> curEntry = map.firstEntry();
            for (int j = 0; j < W - 1; j++) {
                Map.Entry<Integer, Integer> nextEntry = map.higherEntry(curEntry.getKey());
                if (nextEntry == null || nextEntry.getKey() != curEntry.getKey() + 1) {
                    return false;
                }

                if (curEntry.getValue() == 1) {
                    map.remove(curEntry.getKey());
                } else {
                    map.put(curEntry.getKey(), curEntry.getValue() - 1);
                }

                curEntry = nextEntry;
            }

            if (curEntry.getValue() == 1) {
                map.remove(curEntry.getKey());
            } else {
                map.put(curEntry.getKey(), curEntry.getValue() - 1);
            }
        }

        return true;
    }

    public boolean isNStraightHand2(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        if (W == 1) {
            return true;
        }

        Arrays.sort(hand);

        int startIndex = 0;


        for (int i = 0; i < hand.length / W; i++) {
            int curIndex = startIndex;
            int bakStartIndex = startIndex;
            int nextIndex = curIndex + 1;
            int j = 0;
            while (j < W - 1) {
                if (nextIndex >= hand.length) {
                    return false;
                }

                if (hand[nextIndex] == Integer.MIN_VALUE || hand[nextIndex] == hand[curIndex]) {
                    if (hand[nextIndex] == hand[curIndex] && startIndex == bakStartIndex) {
                        startIndex = nextIndex;
                    }
                    nextIndex++;
                    continue;
                }

                if (hand[nextIndex] != hand[curIndex] + 1) {
                    return false;
                }

                hand[curIndex] = Integer.MIN_VALUE;
                curIndex = nextIndex;
                nextIndex++;
                ++j;
            }

            hand[curIndex] = Integer.MIN_VALUE;

            if (startIndex == bakStartIndex) {
                startIndex = curIndex + 1;
            }
        }

        return true;

    }

    @Test
    public void doTest() {
        LK846 obj = new LK846();
        int[] nums = new int[]{1, 1, 2, 2, 3, 3};
        obj.isNStraightHand2(nums, 2);

    }
}
