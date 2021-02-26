//爱丽丝有一手（hand）由整数数组给定的牌。 
//
// 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。 
//
// 如果她可以完成分组就返回 true，否则返回 false。 
//
// 
//
// 注意：此题目与 1296 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-co
//nsecutive-numbers/ 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
//输出：true
//解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。 
//
// 示例 2： 
//
// 
//输入：hand = [1,2,3,4,5], W = 4
//输出：false
//解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。 
//
// 
//
// 提示： 
//
// 
// 1 <= hand.length <= 10000 
// 0 <= hand[i] <= 10^9 
// 1 <= W <= hand.length 
// 
// Related Topics Ordered Map 
// 👍 92 👎 0


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    14:22	info
//    解答成功:
//    执行耗时:66 ms,击败了30.43% 的Java用户
//    内存消耗:39.9 MB,击败了18.18% 的Java用户
    public boolean isNStraightHand2(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        if (W == 1) {
            return true;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < hand.length; i++) {
            int key = hand[i];
            map.put(key, map.containsKey(key)? map.get(key)+1 : 1);
        }

        for (int i = 0; i < hand.length/W; i++) {
            Map.Entry<Integer, Integer> curEntry = map.firstEntry();
            if (curEntry==null){
                return false;
            }

            for (int j = 0; j < W-1; j++) {
                Map.Entry<Integer, Integer> nextEntry = map.higherEntry(curEntry.getKey());
                if (nextEntry==null || nextEntry.getKey()!=curEntry.getKey()+1){
                    return false;
                }

                if (curEntry.getValue()==1){
                    map.remove(curEntry.getKey());
                } else {
                    map.put(curEntry.getKey(), curEntry.getValue()-1);
                }

                curEntry = nextEntry;
            }

            if (curEntry.getValue()==1){
                map.remove(curEntry.getKey());
            } else {
                map.put(curEntry.getKey(), curEntry.getValue()-1);
            }
        }

        return true;
    }

//14:42	info
//    解答成功:
//    执行耗时:11 ms,击败了91.67% 的Java用户
//    内存消耗:39.1 MB,击败了78.41% 的Java用户
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        if (W == 1) {
            return true;
        }

        Arrays.sort(hand);

        int startIndex = 0;
        for (int i = 0; i < hand.length/W; i++) {
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
}
//leetcode submit region end(Prohibit modification and deletion)
