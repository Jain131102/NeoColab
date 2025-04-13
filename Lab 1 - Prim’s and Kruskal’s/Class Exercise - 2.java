// 🏙️ Problem Statement: Fiber Optic Network Optimization

// You're assisting urban planners to optimize the installation of a fiber optic network between several buildings. 
// Each building is represented as a node, and the cost of laying a fiber optic cable between two buildings is represented as a weighted edge in a graph.

// Your task is to implement **Prim's Algorithm** to compute the **Minimum Spanning Tree (MST)** — 
// ensuring all buildings are connected at the minimum possible cost without forming any cycles.

// 📥 Input Format:
// • The first line contains an integer `N` — the number of buildings.
// • The next `N` lines each contain `N` space-separated integers, forming an `N × N` cost matrix.
//   - The element at the ith row and jth column denotes the cost of connecting building `i` to building `j`.
//   - A value of `0` means there is no direct connection between the buildings.

// 📤 Output Format:
// • Print `"Spanning Tree Matrix:"` followed by an `N × N` matrix where only the edges that are part of the MST retain their original weights (others are 0).
// • Then, print `"Total Cost: "` followed by the total cost of connecting all buildings in the MST.

// 🔒 Constraints:
// • 1 ≤ N ≤ 10

// 🧪 Sample Test Case 1:
// Input:
// 6
// 0 3 1 6 0 0
// 3 0 5 0 3 0
// 1 5 0 5 6 4
// 6 0 5 0 0 2
// 0 3 6 0 0 6
// 0 0 4 2 6 0

// Output:
// Spanning Tree Matrix:
// 0 3 1 0 0 0 
// 3 0 0 0 3 0 
// 1 0 0 0 0 4 
// 0 0 0 0 0 2 
// 0 3 0 0 0 0 
// 0 0 4 2 0 0 
// Total Cost: 13

// 🧪 Sample Test Case 2:
// Input:
// 4
// 0 2 3 3
// 2 0 0 2
// 3 0 0 4
// 3 2 4 0

// Output:
// Spanning Tree Matrix:
// 0 2 3 0 
// 2 0 0 2 
// 3 0 0 0 
// 0 2 0 0 
// Total Cost: 7

import java.util.Scanner;

 class PrimsAlgorithm {
    private static final int INF = 9999;
    private int[][] graph;
    private int[][] spanning;
    private int n;

    public PrimsAlgorithm(int n) {
        this.n = n;
        graph = new int[n][n];
        spanning = new int[n][n];
    }

    public void readGraph(Scanner scanner) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
    }

    public int prims() {
        int[] distance = new int[n];
        int[] from = new int[n];
        boolean[] visited = new boolean[n];
        int[][] cost = new int[n][n];

        // Initialize cost matrix and spanning tree
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0)
                    cost[i][j] = INF;
                else
                    cost[i][j] = graph[i][j];
                spanning[i][j] = 0;
            }
        }

        distance[0] = 0;
        visited[0] = true;
        for (int i = 1; i < n; i++) {
            distance[i] = cost[0][i];
            from[i] = 0;
            visited[i] = false;
        }

        int minCost = 0;
        int edges = n - 1;

        while (edges > 0) {
            int minDistance = INF;
            int v = -1;

            for (int i = 1; i < n; i++) {
                if (!visited[i] && distance[i] < minDistance) {
                    v = i;
                    minDistance = distance[i];
                }
            }

            int u = from[v];
            spanning[u][v] = distance[v];
            spanning[v][u] = distance[v];
            edges--;
            visited[v] = true;

            for (int i = 1; i < n; i++) {
                if (!visited[i] && cost[i][v] < distance[i]) {
                    distance[i] = cost[i][v];
                    from[i] = v;
                }
            }

            minCost += cost[u][v];
        }
        return minCost;
    }

    public void printSpanningTree() {
        System.out.println("Spanning Tree Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(spanning[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PrimsAlgorithm primsAlgo = new PrimsAlgorithm(n);
        primsAlgo.readGraph(scanner);
        int totalCost = primsAlgo.prims();
        primsAlgo.printSpanningTree();
        System.out.print("Total Cost: " + totalCost);
        scanner.close();
    }
}
