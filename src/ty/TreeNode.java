package ty;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    TreeNode left;
    TreeNode right;
    String str;
    static boolean isSwitch = false;

    public TreeNode(String str) {
        this.str = str;
    }

    public void printAll() {
        if (left != null)
            left.printAll();
        if (right != null)
            right.printAll();
        System.out.println(str);
    }

    //层序遍历
    public static void levelOrder(TreeNode Node) {
        if (Node == null) {
            return;
        }

        TreeNode treeNode;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(Node);

        while (queue.size() != 0) {
            treeNode = queue.poll();

            System.out.print(treeNode.str + " ");

            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            System.out.println();
        }
    }

    /**
     * 判断两个树是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean superCompair(TreeNode a, TreeNode b) {
        //同样是空就是相等，其中一个是空必然不相等;
        if (a == null && b == null) return true;
        else if (a == null || b == null) return false;
        if (a.str.equals(b.str)) {
            //直接比照左右节点，或者一次换枝叶比较是否相等;
            boolean switchResult = true;//控制是否已经交换
            //左右枝的交换
            if (!isSwitch) {
                isSwitch = true;
                switchResult = superCompair(a.right, b.left) && superCompair(a.left, b.right);
                isSwitch = false;
            }
//            if (!isSwitch) {
//                isSwitch = true;
//                if (isOp(a.str)) {
//                    if (a.str.equals("*") && a.left.str.equals("*")) {
//
//                    }
//                    TreeNode temp = new TreeNode(a.str);
//                }
//            }
            //
            return (superCompair(a.left, b.left) && superCompair(a.right, b.right)) || switchResult;
        } else return false;
    }

    public static boolean isOp(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return true;
        }
        return false;
    }

    public static TreeNode suffixExpression2Tree(String suffixStr) {
        if (isEmpty(suffixStr)) return null;
        //System.out.println(suffixStr);
        String chs[] = suffixStr.split("( )+");

        //System.out.println(Arrays.toString(chs));
        // 用于临时存储节点的栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 遍历所有字符，不是运算符的入栈，是运算符的，将栈中两个节点取出，合成一颗树然后入栈
        for (int i = 0; i < chs.length; i++) {
            if (isOperator(chs[i])) {
                if (stack.isEmpty() || stack.size() < 2) {
                    System.err.println("输入的后缀表达式不正确");
                    return null;
                }
                TreeNode root = new TreeNode(chs[i]);
                root.left = stack.pop();
                root.right = stack.pop();
                stack.push(root);
            } else {
                stack.push(new TreeNode(chs[i]));
            }
        }
        if (stack.isEmpty() || stack.size() > 1) {
            System.err.println("输入的后缀表达式不正确");
            return null;
        }
        return stack.pop();
        //TreeNode.levelOrder(stack.pop());
    }

    public static boolean isEmpty(String s) {
        if (s == null && s.equals("")) return true;
        return false;
    }

    public static boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }
        return false;
    }
}
