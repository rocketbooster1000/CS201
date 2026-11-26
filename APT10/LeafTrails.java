// package APT10;

import java.util.TreeMap;
import java.util.TreeSet;

public class LeafTrails {
    public String[] trails(TreeNode tree) {
        TreeSet<LeafInfo> set = new TreeSet<>((a, b) -> a.num - b.num);
        helper(tree, "", set);
        String[] arr = new String[set.size()];
        int i = 0;
        for (LeafInfo info : set) {
            arr[i] = info.path;
            i++;
        }
        return arr;
    }

    public void helper(TreeNode tree, String path, TreeSet<LeafInfo> set) {
        if (tree == null) return;
        if (tree.left == null && tree.right == null) {
            set.add(
                new LeafInfo(tree.info, path)
            );
            return;
        }
        helper(tree.left, path + '0', set);
        helper(tree.right, path + '1', set);
    }

    private static class LeafInfo {
        public int num;
        public String path;

        public LeafInfo(int num, String path) {
            this.num = num;
            this.path = path;
        }
    }
}
