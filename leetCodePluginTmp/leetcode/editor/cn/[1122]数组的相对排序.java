//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 146 👎 0


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        return solution2(arr1, arr2);
    }

    private int[] solution2(int[] arr1, int[] arr2) {
        int[] d = new int[1001];
        for (int i = 0; i < arr1.length; i++) {
            ++d[arr1[i]];
        }

        int[] ans = new int[arr1.length];
        int k = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < d[arr2[i]]; j++) {
                ans[k++] = arr2[i];
            }
            d[arr2[i]] = 0;
        }

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i]; j++) {
                ans[k++] = i;
            }
        }

        return ans;
    }

    //采用HashMap进行计数，通过对Arr2的遍历放置前部分元素，对后部分元素进行排序
    private int[] solution(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            int count = map.containsKey(arr1[i])? map.get(arr1[i])+1: 1;
            map.put(arr1[i], count);
        }

        int k = 0;
        for (int i = 0; i < arr2.length; i++) {
            int count = map.get(arr2[i]);
            while (count>0){
                arr1[k++] = arr2[i];
                --count;
            }
            map.remove(arr2[i]);
        }

        int[] leftValues = map.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(leftValues);
        for (int i = 0; i < leftValues.length; i++) {
            int count = map.get(leftValues[i]);
            while (count>0){
                arr1[k++] = leftValues[i];
                --count;
            }
        }

        return arr1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
