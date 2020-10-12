package ty;

public class ExpChecker {
    public static void main(String[] args) {
        //System.out.println(ExpResolver.creatSuffixExepression("1 + 2 * 3"));
        TreeNode a = TreeNode.buildTreeBysuffix(ExpResolver.creatSuffixExepression("4 - 7 + 6 + 8"));
        TreeNode.levelOrder(a);
        TreeNode b = TreeNode.buildTreeBysuffix(ExpResolver.creatSuffixExepression("59/7 + 3"));
        TreeNode.levelOrder(b);
        System.out.println(TreeNode.superCompair(a,b));
        System.out.println(ExpChecker.isEqual("4 - 7 + 6 + 8","59/7 + 3"));
    }

    /**
     * 查重的判断是否相等
     * @param str1 两个中序表达式
     * @param str2
     * @return 两个结果是否相等
     */
    public static boolean isEqual(String str1,String str2){
        //创建节点
        TreeNode a = TreeNode.buildTreeBysuffix(ExpResolver.creatSuffixExepression(str1));
        TreeNode b = TreeNode.buildTreeBysuffix(ExpResolver.creatSuffixExepression(str2));
        return TreeNode.superCompair(a,b);
    }

}

