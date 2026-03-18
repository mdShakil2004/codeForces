/*
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
    int moves = 0; // global counter for moves

    public int distCandy(Node root) {
        dfs(root);
        return moves;
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        // post-order traversal: left and right
        int left = dfs(node.left);
        int right = dfs(node.right);

        // moves needed at this node = sum of absolute excess from left and right
        moves += Math.abs(left) + Math.abs(right);

        // return net excess candies to parent
        return node.data - 1 + left + right;
    }
}
