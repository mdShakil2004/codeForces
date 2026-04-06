import java.util.*;

class Solution {

    class Node {
        int freq;
        int index;
        Node left, right;

        Node(int freq, int index) {
            this.freq = freq;
            this.index = index;
        }
    }

    public ArrayList<String> huffmanCodes(String s, int f[]) {

        int n = s.length();

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.freq != b.freq) return a.freq - b.freq;
            return a.index - b.index;
        });

        for (int i = 0; i < n; i++) {
            pq.add(new Node(f[i], i));
        }

        if (n == 1) {
            ArrayList<String> res = new ArrayList<>();
            res.add("0");
            return res;
        }

        while (pq.size() > 1) {

            Node left = pq.poll();
            Node right = pq.poll();

            Node newNode = new Node(
                left.freq + right.freq,
                Math.min(left.index, right.index)
            );

            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        Node root = pq.poll();

        ArrayList<String> result = new ArrayList<>();
        generate(root, "", result);

        // 🔥 IMPORTANT FIX
        Collections.sort(result);

        return result;
    }

    private void generate(Node root, String code, ArrayList<String> result) {

        if (root == null) return;

        if (root.left == null && root.right == null) {
            if (code.length() == 0) code = "0";
            result.add(code);
            return;
        }

        generate(root.left, code + "0", result);
        generate(root.right, code + "1", result);
    }
}
