// package APT10;

import java.util.Set;
import java.util.TreeSet;

public class UniqueTreeValues {
    public int[] unique(TreeNode tree) {
        TreeSet<Integer> set = new TreeSet<>();
        helper(tree, set);
        int[] arr = new int[set.size()];
        int i = 0;
        for (int num : set) {
            arr[i] = num;
            i++;
        }
        return arr;
    }

    private void helper(TreeNode tree, Set<Integer> set) {
        if (tree == null) return;
        set.add(tree.info);
        helper(tree.left, set);
        helper(tree.right, set);
    }
}
