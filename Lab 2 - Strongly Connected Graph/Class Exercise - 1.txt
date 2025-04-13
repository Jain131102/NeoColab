// 🖧 Problem Statement: Identifying Strongly Connected Components

// David, a network administrator, is analyzing the connectivity of a directed network of servers. 
// He wants to find groups of servers that are **strongly connected** — 
// that is, there exists a **path from every server to every other server within the group**, and vice versa.

// Your task is to write a program to identify all the **Strongly Connected Components (SCCs)** in the given directed graph.

// 📥 Input Format:
// • The first line contains an integer `V` — the number of servers (nodes).
// • The second line contains an integer `E` — the number of directed connections (edges).
// • The next `E` lines each contain two integers `u` and `v` representing a directed edge from server `u` to server `v`.

// 📤 Output Format:
// • Print `"Strongly Connected Components are:"`
// • Then, for each SCC, print a single line containing the nodes in that component, space-separated.
// • Each SCC can be printed in any order, and the nodes within a component can also be in any order.

// 🔒 Constraints:
// • 1 ≤ V ≤ 10  
// • 1 ≤ E ≤ 10  

// 🧪 Sample Test Case 1:
// Input:
// 4
// 4
// 1 2
// 2 3
// 3 4
// 4 1

// Output:
// Strongly Connected Components are:
// 1 2 3 4

// 🧪 Sample Test Case 2:
// Input:
// 5
// 5
// 1 3
// 1 4
// 2 1
// 3 2
// 4 5

// Output:
// Strongly Connected Components are:
// 1 2 3
// 4
// 5

// 🧠 Explanation:
// • In Sample 1, the graph forms a cycle: 1 → 2 → 3 → 4 → 1. So all nodes are part of one SCC.
// • In Sample 2, nodes 1, 2, and 3 form a cycle. Node 4 can reach 5, but 5 cannot reach 4, so they form separate SCCs.

import java.util.Scanner;

public class Main {

    static final int MAX = 100;

    // Depth-First Search (DFS) to check if there's a path from curr to des
    static boolean dfs(int curr, int des, int[][] adj, int[] vis, int n) {
        if (curr == des) {
            return true;
        }
        vis[curr] = 1;
        for (int i = 1; i <= n; i++) {
            if (adj[curr][i] == 1 && vis[i] == 0) {
                if (dfs(i, des, adj, vis, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper function to check if there's a path between src and des
    static boolean isPath(int src, int des, int[][] adj, int n) {
        int[] vis = new int[MAX];
        return dfs(src, des, adj, vis, n);
    }

    // Function to find and print the strongly connected components
    static void findSCC(int n, int[][] edges, int edgeCount) {
        int[][] adj = new int[MAX][MAX];
        int[] is_scc = new int[MAX];

        // Fill adjacency matrix
        for (int i = 0; i < edgeCount; i++) {
            adj[edges[i][0]][edges[i][1]] = 1;
        }

        System.out.println("Strongly Connected Components are:");

        // Find SCCs
        for (int i = 1; i <= n; i++) {
            if (is_scc[i] == 0) {
                int[] scc = new int[MAX];
                int scc_size = 0;
                scc[scc_size++] = i;

                for (int j = i + 1; j <= n; j++) {
                    if (is_scc[j] == 0 && isPath(i, j, adj, n) && isPath(j, i, adj, n)) {
                        is_scc[j] = 1;
                        scc[scc_size++] = j;
                    }
                }

                // Print the SCC
                for (int k = 0; k < scc_size; k++) {
                    System.out.print(scc[k] + " ");
                }
                System.out.println();
            }
        }
    }

    // Main function to take input and call the findSCC function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();  // Number of servers (nodes)
        int E = scanner.nextInt();  // Number of connections (edges)

        int[][] edges = new int[MAX][2];
        for (int i = 0; i < E; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        // Find and print the strongly connected components
        findSCC(V, edges, E);

        scanner.close();
    }
}
