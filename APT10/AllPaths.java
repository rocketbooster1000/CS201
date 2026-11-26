// package APT10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllPaths {
    public String[] paths(TreeNode t) {
        ArrayList<String> list = new ArrayList<>();
        helper(t, "", list);
        list.sort(Comparator.naturalOrder());
        return list.toArray(new String[]{});
    }

    private void helper(TreeNode t, String path, List<String> list) {
        if (t == null) return;
        path += t.info;
        if (t.left == null && t.right == null) {
            list.add(path);
            return;
        }
        path += "->";
        helper(t.left, path, list);
        helper(t.right, path, list);
    }
}
