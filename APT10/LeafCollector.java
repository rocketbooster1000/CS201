// package APT10;

import java.util.ArrayList;
import java.util.List;

public class LeafCollector {
    public String[] getLeaves(TreeNode tree) {
        ArrayList<String> list = new ArrayList<>();
        while (tree != null) {
            list.add("");
            tree = helper(tree, list);
        }
        return list.toArray(new String[]{});
    }

    public TreeNode helper(TreeNode tree, List<String> list) {
        if (tree == null) return null;
        if (tree.left == null && tree.right == null) {
            list.set(list.size() - 1, list.get(list.size() - 1) + (list.get(list.size() - 1).length() == 0 ? "" : " ") + tree.info);
            return null;
        }
        tree.left = helper(tree.left, list);
        tree.right = helper(tree.right, list);
        return tree;
    }
}
