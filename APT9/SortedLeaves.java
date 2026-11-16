// package APT9;

import java.util.Arrays;
import java.util.Collections;

public class SortedLeaves {
    public String[] values(TreeNode tree) {
        if (tree == null) return new String[]{};
        if (tree.left == null && tree.right == null)
            return new String[]{"" + (char)(tree.info)};
        String[] left = values(tree.left);
        String[] right = values(tree.right);

        String[] arr = new String[left.length + right.length];
        int i = 0;

        for (String s : left) {
            arr[i] = s;
            i++;
        }

        for (String s : right) {
            arr[i] = s;
            i++;
        }

        Arrays.sort(arr);
        return arr;
    }
}
