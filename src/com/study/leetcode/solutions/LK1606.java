package com.study.leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.*;

public class LK1606 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        ArrayList<TreeMap<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(null);
        }

        for (int i = 0; i < arrival.length; i++) {
            int startIndex = i%k;
            int curServerIndex = startIndex;
            TreeMap<Integer, Integer> curServer;
            do {
                curServer = list.get(curServerIndex);

                if (curServer == null) {
                    curServer = new TreeMap<Integer, Integer>();
                    list.set(startIndex, curServer);
                    curServer.put(arrival[i], arrival[i] + load[i]);
                    break;
                } else {
                    Map.Entry<Integer, Integer> floorEntry = curServer.floorEntry(arrival[i]);
                    if (floorEntry==null || floorEntry.getValue()<=arrival[i]){
                        curServer.put(arrival[i], arrival[i] + load[i]);
                        break;
                    }
                }
                curServerIndex = (curServerIndex + 1)%k;
            } while(startIndex!=curServerIndex);
        }

        List<Integer> ans = new ArrayList<>();
        int maxSize = 0;
        for (int i = 0; i < list.size(); i++) {
            TreeMap<Integer, Integer> curServer = list.get(i);
            if (curServer==null || curServer.size()<maxSize){
                continue;
            } else if (curServer.size()==maxSize){
                ans.add(i);
            } else {
                ans.clear();
                ans.add(i);
                maxSize = curServer.size();
            }
        }

        return ans;
    }

    @Test
    public void doTest(){
        LK1606 obj = new LK1606();
        int[] arrival = new int[]{1};
        int[] load = new int[]{1000000000};
        obj.busiestServers(3, arrival, load);
    }
}
