1class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int count = 0;
    }

    TrieNode root = new TrieNode();

    // Insert a word into trie
    void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.child[idx] == null) {
                curr.child[idx] = new TrieNode();
            }
            curr = curr.child[idx];
            curr.count++;
        }
    }

    public ArrayList<String> findPrefixes(String[] arr) {

        // Build Trie
        for (String word : arr) {
            insert(word);
        }

        ArrayList<String> ans = new ArrayList<>();

        // Find shortest unique prefix
        for (String word : arr) {
            TrieNode curr = root;
            StringBuilder prefix = new StringBuilder();

            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                curr = curr.child[idx];
                prefix.append(ch);

                if (curr.count == 1)
                    break;
            }

            ans.add(prefix.toString());
        }

        return ans;
    }
}
