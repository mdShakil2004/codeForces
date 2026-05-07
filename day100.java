/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Solution {

    // Function to check if two trees are identical
    boolean isIdentical(Node a, Node b) {

        // Both are null
        if (a == null && b == null)
            return true;

        // One is null and other is not
        if (a == null || b == null)
            return false;

        // Check current node and subtrees
        return (a.data == b.data)
                && isIdentical(a.left, b.left)
                && isIdentical(a.right, b.right);
    }

    public boolean isSubTree(Node root1, Node root2) {

        // Empty tree is always a subtree
        if (root2 == null)
            return true;

        // Main tree empty but subtree not empty
        if (root1 == null)
            return false;

        // If trees match at current node
        if (isIdentical(root1, root2))
            return true;

        // Otherwise check left and right subtrees
        return isSubTree(root1.left, root2)
                || isSubTree(root1.right, root2);
    }
}
