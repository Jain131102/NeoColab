import java.util.*;

class Solution {
    public void dfs(int node, int vis[], ArrayList<ArrayList<Integer>> adjT, List<Integer> component) {
        vis[node] = 1;
        component.add(node);  // collect node in current SCC
        for (int y : adjT.get(node)) {
            if (vis[y] == 0)
                dfs(y, vis, adjT, component);
        }
    }

    public void dfs1(int node, int vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> s) {
        vis[node] = 1;
        for (int y : adj.get(node)) {
            if (vis[y] == 0)
                dfs1(y, vis, adj, s);
        }
        s.push(node);
    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> s = new Stack<>();
        int vis1[] = new int[V];

        // 1st pass: get finishing order
        for (int i = 0; i < V; i++) {
            if (vis1[i] == 0) {
                dfs1(i, vis1, adj, s);
            }
        }

        // Reverse graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int y : adj.get(i))
                adjT.get(y).add(i);
        }

        // 2nd pass: DFS in finishing order on transposed graph
        int count = 0;
        int vis[] = new int[V];
        System.out.println("Strongly Connected Components:");
        while (!s.isEmpty()) {
            int x = s.pop();
            if (vis[x] == 0) {
                List<Integer> component = new ArrayList<>();
                dfs(x, vis, adjT, component);
                count++;
                System.out.println(component);
            }
        }
        return count;
    }

    // Main method
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = { {0, 2}, {1, 0}, {2, 1}, {0, 3}, {3, 4} };

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        Solution sol = new Solution();
        int sccCount = sol.kosaraju(V, adj);

        System.out.println("Total SCCs: " + sccCount);
    }
}
