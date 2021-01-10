//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 424 👎 0


import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        return solutionForSort(nums1, nums2);
    }

    private int[] solutionForSort(int[] nums1, int[] nums2) {
        if (nums1 ==null || nums2 ==null){
            return new int[]{};
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> lst = new ArrayList<>();
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
