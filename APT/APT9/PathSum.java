// package APT9;

public class PathSum {
    public int hasPath(int target, TreeNode tree) {
        if (tree == null) return 0;
        if (tree.left == null && tree.right == null) {
            return (target == tree.info) ? 1 : 0;
        }
        if (hasPath(target - tree.info, tree.left) == 1 || hasPath(target - tree.info, tree.right) == 1) {
            return 1;
        }
        return 0;
    }
}
