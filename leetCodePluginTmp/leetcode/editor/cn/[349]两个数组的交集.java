//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 299 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        return solutionForSort(nums1, nums2);
    }

    //利用集合
    //解答成功:
    //			执行耗时:6 ms,击败了25.59% 的Java用户
    //			内存消耗:38.4 MB,击败了93.21% 的Java用户
    private int[] solution(int[] nums1, int[] nums2) {
        if (nums1 ==null || nums2 ==null){
            return new int[0];
        }

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }
        return getintersection(set1, set2);
    }

    private int[] getintersection(Set<Integer> set1, Set<Integer> set2){
        if (set1.size()>set2.size()){
            return getintersection(set2, set1);
        }

        Set<Integer> intersectionSet = new HashSet<Integer>();
        for (Integer num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }

        return intersectionSet.stream().mapToInt(Integer::intValue).toArray();
    }

    //解答成功:
    //			执行耗时:5 ms,击败了30.88% 的Java用户
    //			内存消耗:38.5 MB,击败了89.23% 的Java用户
    private int[] solutionForSort(int[] nums1, int[] nums2) {
        if (nums1 ==null || nums2 ==null){
            return new int[]{};
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        HashSet<Integer> lst = new HashSet<>();
        int i = 0, j = 0;
        while (i< nums1.length && j< nums2.length){
            if (nums1[i] == nums2[j]){
                lst.add(nums1[i]);
                ++i;
                ++j;
            }
            else if (nums1[i]< nums2[j]){
                ++i;
            }
            else{
                ++j;
            }
        }
        return lst.stream().mapToInt(Integer::intValue).toArray();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
