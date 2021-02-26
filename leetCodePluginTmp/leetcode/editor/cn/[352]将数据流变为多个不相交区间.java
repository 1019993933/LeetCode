//给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。 
//
// 例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为： 
//
// [1, 1]
//[1, 1], [3, 3]
//[1, 1], [3, 3], [7, 7]
//[1, 3], [7, 7]
//[1, 3], [6, 7]
// 
//
// 
//
// 进阶： 
//如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办? 
//
// 提示： 
//特别感谢 @yunhong 提供了本问题和其测试用例。 
// Related Topics 二分查找 Ordered Map 
// 👍 57 👎 0


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class SummaryRanges {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    /** Initialize your data structure here. */
    public SummaryRanges() {

    }
    
    public void addNum(int val) {
        if (!map.containsKey(val)){
            Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(val);
            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(val);

            if (floorEntry!=null && floorEntry.getValue()==val-1 && ceilingEntry!=null && ceilingEntry.getKey()-1==val){
                map.put(floorEntry.getKey(), ceilingEntry.getValue());
                map.remove(ceilingEntry.getKey());
            } else if (floorEntry!=null && floorEntry.getValue()==val-1){
                map.put(floorEntry.getKey(), val);
            } else if (ceilingEntry!=null && ceilingEntry.getKey()-1==val){
                map.put(val, ceilingEntry.getValue());
                map.remove(ceilingEntry.getKey());
            } else{
                map.put(val, val);
            }
        }
    }
    
    public int[][] getIntervals() {
        if (map.size()==0){
            return new int[][]{};
        }

        int[][] ans = new int[map.size()][2];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            ans[i][0] = entry.getKey();
            ans[i][1] = entry.getValue();
            ++i;
        }

        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
//leetcode submit region end(Prohibit modification and deletion)
