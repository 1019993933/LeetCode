//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1059 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private static final Map<Integer, String> baseMap = new HashMap<>();
    {
        baseMap.put(2, "abc");
        baseMap.put(3, "def");
        baseMap.put(4, "ghi");
        baseMap.put(5, "jkl");
        baseMap.put(6, "mno");
        baseMap.put(7, "pqrs");
        baseMap.put(8, "tuv");
        baseMap.put(9, "wxyz");
    }
    //解题思路1：队列
    class Solution1{
        private Queue<String> queue = new LinkedList<>();

        public List<String> letterCombinations(String digits) {
            if (digits==null || digits.length()<=0){
                return new ArrayList<>();
            }

            for (int i = 0; i < digits.length(); i++) {
                int key = digits.charAt(i) - '0';
                String str = baseMap.get(key);
                if (queue.isEmpty()) {
                    for (char ch : str.toCharArray()) {
                        queue.add(String.valueOf(ch));
                    }
                } else
                {
                    int n  = queue.size();
                    while(n>0){
                        String queueStr = queue.poll();
                        for (char ch : str.toCharArray()) {
                            queue.add(queueStr+ch);
                        }
                        n--;
                    }
                }
            }

            List<String> ans = new ArrayList<>();
            ans.addAll(queue);
            return ans;
        }
    }

    public List<String> letterCombinations(String digits) {
        Solution1 solution = new Solution1();
        return solution.letterCombinations(digits);
    }






    //解题思路2：建立2~9对就的树结构，如以2为根的树对应a,b,c三个子节点， 3为根的树对应d,e,f三个子节点
    //          对于待求解题进行逆序遍历，将当前数字对应
//    public List<String> letterCombinations(String digits) {
//        if (digits==null || digits.length()==0){
//            return new ArrayList<>();
//        }
//
//        TreeNode root = initTree(null, digits);
//        List<String> ans = new ArrayList<>();
//
//        for (TreeNode child : root.children) {
//            dfs(child, "", ans);
//        }
//
//        return ans;
//    }

    private void dfs(TreeNode root, String curStr, List<String> lst) {
        if (root == null) {
            return;
        }
        if (root.children.size() == 0) {
            curStr += root.value;
            lst.add(curStr);
            return;
        }

        for (TreeNode child : root.children) {
            dfs(child, curStr + root.value, lst);
        }
    }


    private TreeNode initTree(TreeNode node, String digits) {
        for (int i = digits.length()-1; i >=0; i--) {
            char curCh = digits.charAt(i);
            TreeNode curNode = (TreeNode)getRootNode(curCh);
            for (TreeNode child : curNode.children) {
                if (node!=null) {
                    TreeNode cloneNode = (TreeNode) node.clone();
                    child.children.addAll(cloneNode.children);
                }
            }
            node = curNode;
        }
        return node;
    }

    private Object getRootNode(char ch) {
        switch (ch) {
            case '2':
                return _2.clone();
            case '3':
                return _3.clone();
            case '4':
                return _4.clone();
            case '5':
                return _5.clone();
            case '6':
                return _6.clone();
            case '7':
                return _7.clone();
            case '8':
                return _8.clone();
            case '9':
                return _9.clone();
            default:
                return null;
        }
    }

    private TreeNode _2 = genTreeNode(new char[]{'2', 'a', 'b', 'c'});
    private TreeNode _3 = genTreeNode(new char[]{'3', 'd', 'e', 'f'});
    private TreeNode _4 = genTreeNode(new char[]{'4', 'g', 'h', 'i'});
    private TreeNode _5 = genTreeNode(new char[]{'5', 'j', 'k', 'l'});
    private TreeNode _6 = genTreeNode(new char[]{'6', 'm', 'n', 'o'});
    private TreeNode _7 = genTreeNode(new char[]{'7', 'p', 'q', 'r', 's'});
    private TreeNode _8 = genTreeNode(new char[]{'8', 't', 'u', 'v'});
    private TreeNode _9 = genTreeNode(new char[]{'9', 'w', 'x', 'y', 'z'});


    private TreeNode genTreeNode(char[] chars) {
        TreeNode root = new TreeNode(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            root.add(new TreeNode(chars[i]));
        }
        return root;
    }

    public class TreeNode {
        public char value;
        public List<TreeNode> children = new ArrayList<>();

        public TreeNode(char ch) {
            this.value = ch;
        }

        public TreeNode add(TreeNode child) {
            this.children.add(child);
            return this;
        }

        public void addTreeToChild(TreeNode treeRoot) {
            for (TreeNode child : children) {
                child.children.addAll(treeRoot.children);
            }
        }

        @Override
        protected Object clone() {
            TreeNode node = new TreeNode(this.value);
            for (TreeNode child : this.children) {
                TreeNode childNode = (TreeNode) child.clone();
                node.children.add(childNode);
            }

            return node;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
