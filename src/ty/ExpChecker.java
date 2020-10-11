package ty;

public class ExpChecker {
    public static void main(String[] args) {
        //System.out.println(ExpResolver.creatSuffixExepression("1 + 2 * 3"));
        TreeNode a = TreeNode.suffixExpression2Tree(ExpResolver.creatSuffixExepression("4 - 7 + 6 + 8"));
        TreeNode.levelOrder(a);
        TreeNode b = TreeNode.suffixExpression2Tree(ExpResolver.creatSuffixExepression("59/7 + 3"));
        TreeNode.levelOrder(b);
        System.out.println(TreeNode.superCompair(a,b));
        System.out.println(ExpChecker.isEqual("4 - 7 + 6 + 8","59/7 + 3"));
    }

    /**
     * �ж��Ƿ����
     * @param str1 ����������ʽ
     * @param str2
     * @return ��������Ƿ����
     */
    public static boolean isEqual(String str1,String str2){
        //
        TreeNode a = TreeNode.suffixExpression2Tree(ExpResolver.creatSuffixExepression(str1));
        TreeNode.levelOrder(a);
        TreeNode b = TreeNode.suffixExpression2Tree(ExpResolver.creatSuffixExepression(str2));
        TreeNode.levelOrder(b);
        return TreeNode.superCompair(a,b);
    }

}

