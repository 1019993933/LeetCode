// 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
//
//
// 示例 1：
//
//
// 输入：nums = [10,2]
// 输出："210"
//
// 示例 2：
//
//
// 输入：nums = [3,30,34,5,9]
// 输出："9534330"
//
//
// 示例 3：
//
//
// 输入：nums = [1]
// 输出："1"
//
//
// 示例 4：
//
//
// 输入：nums = [10]
// 输出："10"
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 109
//
// Related Topics 排序
// 👍 434 👎 0

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestNumber(int[] nums) {
        StringBuffer buffer = new StringBuffer();

        //由于拼接完成后位数是相同的，所有只需要将子元素进行最高位比较，取高位大的值放在最前面
        //比较时如果位数不同，则将较小值右补0，直到位数相同，再依次比较同位值
        //如 10 与 2比较，先将2置为20，再与10比较 --》拼接最大值为210
        // 9 与870 比较，先将9置为900，再与870比较 --》拼接最大值为9870
        List<Integer> lst = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(lst, new Comparator<Integer>() {
            @Override
            //依次比较最高位的大于
            public int compare(Integer x, Integer y) {

                String a = String.valueOf(x) + String.valueOf(y);
                String b = String.valueOf(y) + String.valueOf(x);

                return a.compareTo(b);
            }
        });


        buffer.setLength(0);
        if (lst.size()>0 && lst.get(lst.size()-1)==0)
        {
            return "0";
        }

        for (int i = lst.size()-1; i >= 0; i--) {
            buffer.append(String.valueOf(lst.get(i)));
        }

        return buffer.toString();
    }
}
// leetcode submit region end(Prohibit modification and deletion)
