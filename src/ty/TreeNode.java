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

    //�������
    public static void levelOrder(TreeNode Node) {
        if (Node == null) {
            return;
        }
        TreeNode treeNode;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(Node);
        //ʹ�ö���ʵ�ֲ�������
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
     * �ж��������Ƿ����
     *   ����֦����
     * @param a
     * @param b
     * @return
     */
    public static boolean superCompair(TreeNode a, TreeNode b) {
        //ͬ���ǿվ�����ȣ�����һ���ǿձ�Ȼ�����;
        if (a == null && b == null) return true;
        else if (a == null || b == null) return false;
        if (a.str.equals(b.str)) {
            //ֱ�ӱ������ҽڵ㣬����һ�λ�֦Ҷ�Ƚ��Ƿ����;
            boolean switchResult = true;//�����Ƿ��Ѿ�����
            //����֦�Ľ���
            //isSwitch�Ǹ���̬����������־�����������Ƿ�任�ģ����߳�Ҫ���������- -
            if ((a.str.equals("+")||a.str.equals("*"))&&!isSwitch) {
                isSwitch = true;
                switchResult = superCompair(a.right, b.left) && superCompair(a.left, b.right);
                isSwitch = false;
            }
            return (superCompair(a.left, b.left) && superCompair(a.right, b.right)) || switchResult;
        } else return false;
    }

    public static boolean isOp(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return true;
        }
        return false;
    }

    /**
     * ͨ����׺���ʽ����һ�Ŷ�����
     * @param suffixStr ��׺���ʽ
     * @return һ�Ŷ�����
     */
    public static TreeNode buildTreeBysuffix(String suffixStr) {
        if (isEmpty(suffixStr)) return null;
        //System.out.println(suffixStr);
        String chs[] = suffixStr.split("( )+");

        //System.out.println(Arrays.toString(chs));
        // ������ʱ�洢�ڵ��ջ
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // ���������ַ����������������ջ����������ģ���ջ�������ڵ�ȡ�����ϳ�һ����Ȼ����ջ
        for (int i = 0; i < chs.length; i++) {
            if (isOperator(chs[i])) {
                if (stack.isEmpty() || stack.size() < 2) {
                    System.err.println("����ĺ�׺���ʽ����ȷ");
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
            System.err.println("����ĺ�׺���ʽ����ȷ");
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
