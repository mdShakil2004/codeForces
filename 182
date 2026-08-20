class Solution {
    int maxDiff(Node root) {
        int[] ans = {Integer.MIN_VALUE};
        findMin(root, ans);
        return ans[0];
    }

    int findMin(Node node, int[] ans) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int leftMin = findMin(node.left, ans);
        int rightMin = findMin(node.right, ans);

        int minDescendant = Math.min(leftMin, rightMin);

        // Current node is ancestor of nodes in its subtree
        if (minDescendant != Integer.MAX_VALUE) {
            ans[0] = Math.max(ans[0], node.data - minDescendant);
        }

        // Minimum value in the subtree rooted at current node
        return Math.min(node.data, minDescendant);
    }
}
