//从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任
//意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// 👍 79 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStraight(int[] nums) {
//        int sum = 0;
//
//        HashSet<Integer> set = new HashSet<>(){
//            {
//                add(0x7);
//                add(0x13);
//                add(0x19);
//                add(0x1C);
//                add(0xF);
//                add(0x17);
//                add(0x1B);
//                add(0x1D);
//                add(0x1F);
//            }};
//
//        // 5个数可能重复，重复为false
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i]!=0)
//            {
//                sum |= 1<<nums[i];
//            }
//        }
//
//        while (sum%2==0){
//            sum >>= 1;
//        }
//
//        return set.contains(sum);

        insertSort(nums);
        if (isRepeatNum(nums))
        {
            return false;
        }
        int i=0;
        while(i<5)
        {
            if (nums[i]!=0) {
                return nums[4] - nums[i] < 5;
            }
            ++i;
        }
        return false;
    }

    public boolean isRepeatNum(int[] nums)
    {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i+1]==nums[i] && nums[i]!=0)
            {
                return true;
            }
        }
        return false;
    }

    public void insertSort(int[] nums)
    {
        for (int i = 0+1; i < nums.length; i++) {
            int x  = nums[i];
            for (int j = i; j > 0; j--) {
                if (nums[j]>=nums[j-1])
                {
                    break;
                }
                swap(nums, j, j-1);
            }
        }

    }

    private void swap(int[] nums, int i, int j)
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
