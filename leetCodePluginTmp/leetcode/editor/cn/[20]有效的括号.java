//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 2014 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        if (s==null || s.length()==0){
            return true;
        }

        if (s.length()<2){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isLeftChar(ch))
            {
                stack.push(ch);
            }

            if (isRightChar(ch))
            {
                if (stack.isEmpty()){
                    return false;
                }
                if (!matchChar(stack.pop(), ch))
                {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isLeftChar(char ch)
    {
        return (ch=='(' || ch=='{' || ch=='[');
    }

    private boolean isRightChar(char ch)
    {
        return (ch==')' || ch=='}' || ch==']');
    }

    private boolean matchChar(char ch1, char ch2)
    {
        return ((ch1=='(' && ch2==')')
            || (ch1=='[' && ch2==']')
            || (ch1=='{' && ch2=='}'));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
