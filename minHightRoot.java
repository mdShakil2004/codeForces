class Solution {
    public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        ArrayList<Integer> result = new ArrayList<>();
        
        // Edge case
        if (V == 1) {
            result.add(0);
            return result;
        }

        // Step 1: Build graph
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Step 2: Find initial leaves
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        // Step 3: Trim leaves
        int remainingNodes = V;

        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                int neighbor = graph.get(leaf).iterator().next();
                graph.get(neighbor).remove(leaf);

                if (graph.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }

            leaves = newLeaves;
        }

        // Step 4: Remaining nodes are answer
        result.addAll(leaves);
        return result;
    }
}
