
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}


class Solution {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        Node pre = null;
        Node suc = null;
        
        Node curr = root;
        
        while (curr != null) {
            if (curr.data < key) {
                pre = curr;
                curr = curr.right;
            } else if (curr.data > key) {
                suc = curr;
                curr = curr.left;
            } else {
                // Find predecessor (max in left subtree)
                if (curr.left != null) {
                    Node temp = curr.left;
                    while (temp.right != null) {
                        temp = temp.right;
                    }
                    pre = temp;
                }
                
                // Find successor (min in right subtree)
                if (curr.right != null) {
                    Node temp = curr.right;
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    suc = temp;
                }
                break;
            }
        }
        
        ArrayList<Node> res = new ArrayList<>();
        res.add(pre);
        res.add(suc);
        return res;
    }
}
