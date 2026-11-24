// package APTQuiz2;

import java.util.HashSet;
import java.util.Set;

public class TreeDistinct {
    public int distinct(TreeNode tree) {
        HashSet<Integer> set = new HashSet<>();        
        helper(tree, set);
        return set.size();
    }

    private void helper(TreeNode tree, Set<Integer> set) {
        if (tree == null) return;
        set.add(tree.info);
        helper(tree.left, set);
        helper(tree.right, set);
    }
}
