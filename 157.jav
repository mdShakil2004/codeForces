/* Structure of Binary Tree Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    int maxLen = 1;

    public int longestConsecutive(Node root) {
        if (root == null) return -1;

        dfs(root, root.data - 1, 0);

        return maxLen == 1 ? -1 : maxLen;
    }

    private void dfs(Node node, int prev, int len) {
        if (node == null) return;

        if (node.data == prev + 1)
            len++;
        else
            len = 1;

        maxLen = Math.max(maxLen, len);

        dfs(node.left, node.data, len);
        dfs(node.right, node.data, len);
    }
}
