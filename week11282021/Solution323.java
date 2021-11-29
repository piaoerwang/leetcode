class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int components = n;
        for (int[] e : edges) {
            int p1 = findParent(parent, e[0]);
            int p2 = findParent(parent, e[1]);
            if (p1 != p2) {
                parent[p1] = p2; // Union 2 component
                components--;
            }
        }
        return components;
    }
    private int findParent(int[] parent, int i) {
        while (i != parent[i]) i = parent[i];
        return i; // Without Path Compression
    }
}
