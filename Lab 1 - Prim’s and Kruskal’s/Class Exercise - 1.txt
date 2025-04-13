// 🧩 Problem Statement

// You're tasked with analyzing a network of interconnected data points, where each data point is represented as a **node**, and the connection between any two nodes is represented as an **edge with a weight**.

// Your goal is to compute the **Minimum Spanning Tree (MST)** of this graph. An MST is a subset of edges that:
//   - Connects all the nodes,
//   - Contains no cycles,
//   - Has the minimum possible total edge weight.

// To accomplish this, implement **Prim’s Algorithm** to find the MST for a given graph.

// 🔽 Input Format:
// • The first line contains an integer `V` — the number of nodes in the graph.
// • The next `V` lines each contain `V` space-separated integers representing the adjacency matrix of the graph.
//   - The element at the ith row and jth column denotes the weight of the edge between node `i` and node `j`.
//   - The weight of a node to itself is always `0` (i.e., `weight[i][i] = 0`).

// 🔼 Output Format:
// Print the edges included in the MST along with their weights, in the following format:
// Edge   Weight  
// u - v    w

// 💡 Constraints:
// • 1 ≤ V ≤ 10  
// • 0 ≤ weight[i][j] ≤ 50  
// • weight[i][i] = 0  

// 📘 Sample Test Case 1:
// Input:
// 3
// 0 2 3
// 2 0 4
// 3 4 0

// Output:
// Edge   Weight
// 0 - 1    2
// 0 - 2    3

// 📗 Sample Test Case 2:
// Input:
// 5
// 0 2 0 6 0
// 2 0 3 8 5
// 0 3 0 0 7
// 6 8 0 0 9
// 0 5 7 9 0

// Output:
// Edge   Weight
// 0 - 1    2
// 1 - 2    3
// 0 - 3    6
// 1 - 4    5

import java.util.Scanner;

class PrimMST {
    private static final int MAX_V = 10;
    private static final int INF = Integer.MAX_VALUE;

    int minKey(int key[], boolean mstSet[], int V) {
        int min = INF, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void printMST(int parent[], int graph[][], int V) {
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
    }

    void primMST(int graph[][], int V) {
        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = INF;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printMST(parent, graph, V);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();

        int graph[][] = new int[MAX_V][MAX_V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        PrimMST mst = new PrimMST();
        mst.primMST(graph, V);

        scanner.close();
    }
}
