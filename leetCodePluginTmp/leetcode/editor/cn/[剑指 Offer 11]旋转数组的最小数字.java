//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/ 
// Related Topics 二分查找 
// 👍 175 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minArray(int[] numbers) {
        return s1(numbers);
    }

    //最简单的解法，由于是递增，遍历找到第一个破坏了递增关系的即为最小值
    //时间复杂度为O(N)
    public int s1(int[] numbers) {
        if (numbers.length <= 0)
        {
            return -1;
        }

        int min = numbers[0];
        for (int i = 0; i < numbers.length-1; i++) {
            if (numbers[i]>numbers[i+1])
            {
                return numbers[i+1];
            }
        }

        return min;
    }

    //优化算法，由于递增关系，对于数组任意一个节点P，其左边满足小于P，右边满足大于等于P，在进行旋转后，必须会破坏这种关系
    //将旋转后的数组分解为两个子递增数组【A1, A2】[B1, B2]，则对于任意值，必须处于其中一个区间，且与别一个区间边界存在破坏递增关系的关系
}
//leetcode submit region end(Prohibit modification and deletion)
