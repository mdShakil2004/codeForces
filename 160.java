160.java
import java.util.*;

class Solution {
    int preIndex = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public Node constructBinaryTree(int[] pre, int[] preMirror) {

        for (int i = 0; i < preMirror.length; i++)
            map.put(preMirror[i], i);

        return solve(pre, preMirror, 0, pre.length - 1);
    }

    private Node solve(int[] pre, int[] preMirror, int l, int h) {

        if (preIndex >= pre.length || l > h)
            return null;

        Node root = new Node(pre[preIndex++]);

        if (l == h || preIndex == pre.length)
            return root;

        int i = map.get(pre[preIndex]);

        root.left = solve(pre, preMirror, i, h);
        root.right = solve(pre, preMirror, l + 1, i - 1);

        return root;
    }
}
