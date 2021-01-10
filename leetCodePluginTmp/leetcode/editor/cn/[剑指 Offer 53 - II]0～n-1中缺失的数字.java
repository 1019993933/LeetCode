//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出
//这个数字。 
//
// 
//
// 示例 1: 
//
// 输入: [0,1,3]
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [0,1,2,3,4,5,6,7,9]
//输出: 8 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 10000 
// Related Topics 数组 二分查找 
// 👍 76 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//方法一：通过求各0~n-1求和，删除数组各的方式可以知道缺少了哪位数
//方法二：通过对0~n-1数进行异或运算， x^0 = x, x^x = 0;
//方法三：二分查找，正常情况数组值等于其下标，找到第一个值大于下标的数，对应下标即为缺失的数，
class Solution {
    public int missingNumber(int[] nums) {
        if (nums==null)
        {
            return 0;
        }
        int start = 0;
        int end = nums.length;
        int i = (start+end)/2;
        while(start<end)
        {
            i = (start+end)/2;
            if (nums[i] > i)
            {
                end = i;
            }
            else
            {
                start = i+1;
            }
        }

        return (nums[i]==i)? i+1: i;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
