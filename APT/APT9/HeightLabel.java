// package APT9;

public class HeightLabel {
    public TreeNode rewire(TreeNode t) {
        if (t == null) return null;
        if (t.left == null && t.right == null) return new TreeNode(1, null, null);
        TreeNode newTree = new TreeNode(0, null, null);
        newTree.left = rewire(t.left);
        newTree.right = rewire(t.right);
        newTree.info = Math.max(
            (newTree.left == null) ? 0 : newTree.left.info, 
            (newTree.right == null) ? 0 : newTree.right.info) + 1;
        return newTree;
    }


}
