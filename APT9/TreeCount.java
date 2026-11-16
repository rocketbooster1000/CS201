// package APT9;

public class TreeCount {
    public int count(TreeNode tree) {
        if (tree == null) return 0;
        if (tree.left == null && tree.right == null) return 1;
        return 1 + count(tree.left) + count(tree.right);
    }

    // public static class TreeNode {
    //     int info;
    //     TreeNode left;
    //     TreeNode right;
    //     TreeNode(int x){
    //         info = x;
    //     }
    //     TreeNode(int x, TreeNode lNode, TreeNode rNode){
    //         info = x;
    //         left = lNode;
    //         right = rNode;
    //     }
    // }
}
