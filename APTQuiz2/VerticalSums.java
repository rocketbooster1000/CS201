// package APTQuiz2;

import java.util.Map;
import java.util.TreeMap;

public class VerticalSums {
    public int[] sums(TreeNode tree) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        helper(tree, 0, map);
        int[] arr = new int[map.entrySet().size()];
        int i = 0;
        for (int val : map.values()) {
            arr[i] = val;
            i++;
        }
        return arr;
    }

    public void helper(TreeNode tree, int column, Map<Integer, Integer> map) {
        if (tree == null) return;
        map.put(column, map.getOrDefault(column, 0) + tree.info);
        helper(tree.left, column - 1, map);
        helper(tree.right, column + 1, map);
    }
}
