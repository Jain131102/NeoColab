// 🚦 Problem Statement: Strongly Connected Components in a Transportation Network

// A transportation network connects several towns in a region. Each town is represented as a vertex, 
// and each directed road between towns is represented as a directed edge.

// The objective is to identify groups of towns that are **strongly connected**. 
// A group is strongly connected if there is a **path from every town in the group to every other town** 
// in the group using the directed roads.

// Your task is to write a program that finds and prints all the **strongly connected components (SCCs)** 
// in the network.

// 📥 Input Format:
// • The first line contains an integer `n` — the number of directed roads.
// • The next `n` lines each contain two integers `a` and `b` — indicating a road from town `a` to town `b`.

// 📤 Output Format:
// • Print the strongly connected components of the network, one per line.
// • Each component should be a space-separated list of towns (integers).
// • The order of towns and the order of components can be arbitrary.

// 🔒 Constraints:
// • 0 ≤ a, b < n
// • Town labels range from 0 to the maximum node index found in the input.

// 🧪 Sample Test Case 1:
// Input:
// 3
// 0 1
// 1 2
// 0 2

// Output:
// 0
// 1
// 2

// 🧪 Sample Test Case 2:
// Input:
// 4
// 0 2
// 2 3
// 1 3
// 0 1

// Output:
// 0
// 1
// 2
// 3

// 🧠 Notes:
// • Each SCC is a group of nodes with full two-way reachability.
// • In these test cases, no cycles exist, so every node is its own SCC.


import java.util.*;

class StronglyConnectedComponents {

    // Class to represent a directed graph
    static class Graph {
        int V;
        List<List<Integer>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        // Add directed edge
        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        // Fill order of vertices for the first DFS
        void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            for (Integer neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    fillOrder(neighbor, visited, stack);
                }
            }
            stack.push(v);
        }

        // Get the transpose (reverse) of the graph
        Graph getTranspose() {
            Graph gT = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (Integer neighbor : adj.get(v)) {
                    gT.addEdge(neighbor, v);
                }
            }
            return gT;
        }

        // Perform DFS and print the component
        void DFS(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");
            for (Integer neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    DFS(neighbor, visited);
                }
            }
        }

        // Print all SCCs using Kosaraju's algorithm
        void printSCCs() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[V];

            // Fill vertices in stack according to their finishing times
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    fillOrder(i, visited, stack);
                }
            }

            // Get the transpose (reverse) of the graph
            Graph gT = getTranspose();

            // Mark all vertices as not visited for second DFS
            Arrays.fill(visited, false);

            // Process all vertices in order defined by stack
            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visited[v]) {
                    gT.DFS(v, visited);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of roads (edges)
        int n = sc.nextInt();

        // Ensure that the number of roads is valid
        if (n <= 0) {
            System.out.println("No roads in the graph.");
            return;
        }

        // Create graph
        Graph graph = new Graph(n);

        // Read the roads (edges)
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.addEdge(a, b);
        }

        // Print strongly connected components
        graph.printSCCs();
    }
}


// 🚧 Problem Statement: Making a City Strongly Connected

// Lena, a transportation planner, is analyzing a city's road network to improve connectivity. 
// The city has `n` intersections (numbered from 1 to n) and `m` one-way streets connecting them. 
// Lena’s goal is to determine the **minimum number of additional one-way streets** required to make the 
// entire network **strongly connected**.

// A **strongly connected** road network means that **every intersection can be reached from every other 
// intersection** using the available one-way streets.

// Your task is to help Lena compute the fewest number of additional streets needed to achieve this level of connectivity.

// 📥 Input Format:
// • The first line contains an integer `n` — the number of intersections (vertices).
// • The second line contains an integer `m` — the number of one-way streets (edges).
// • The next `m` lines each contain two integers `u` and `v` — indicating a one-way street from intersection `u` to intersection `v`.

// 📤 Output Format:
// • Output a single integer — the minimum number of new one-way streets required to make the entire road network strongly connected.

// 🔒 Constraints:
// • 1 ≤ n ≤ 10
// • 1 ≤ m ≤ 10
// • 1 ≤ u, v ≤ n

// 🧪 Sample Test Case 1:
// Input:
// 3
// 3
// 1 2
// 2 3
// 3 1

// Output:
// 0

// 🧪 Sample Test Case 2:
// Input:
// 4
// 3
// 1 2
// 2 3
// 3 4

// Output:
// 1

// 🧠 Explanation:
// • In the first test case, the graph is already strongly connected: every node can reach every other node.
// • In the second case, adding a one-way street from node 4 to node 1 would complete a cycle, making the network strongly connected.


import java.util.Scanner;

public class Main {

    static final int MAX = 100;

    static void dfs(int v, int[][] adj, int[] vis, int n) {
        vis[v] = 1;
        for (int i = 1; i <= n; i++) {
            if (adj[v][i] == 1 && vis[i] == 0) {
                dfs(i, adj, vis, n);
            }
        }
    }

    static void transposeGraph(int[][] adj, int[][] trans, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trans[j][i] = adj[i][j];
            }
        }
    }

    static int findSCCs(int[][] adj, int n, int[] inDeg, int[] outDeg) {
        int[] vis = new int[MAX];
        int[] component = new int[MAX];
        int sccCount = 0;

        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                dfs(i, adj, vis, n);
                component[i] = ++sccCount;
            }
        }

        int[][] trans = new int[MAX][MAX];
        transposeGraph(adj, trans, n);

        for (int i = 1; i <= n; i++) {
            vis[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                dfs(i, trans, vis, n);
            }
        }

        int sources = 0, sinks = 0;
        
        for (int i = 1; i <= n; i++) {
            if (inDeg[i] == 0) sources++;
            if (outDeg[i] == 0) sinks++;
        }
        return Math.max(sources, sinks);
    }

    static int findMinimumEdges(int[] source, int[] dest, int n, int m) {
        int[][] adj = new int[MAX][MAX];
        int[] inDeg = new int[MAX];
        int[] outDeg = new int[MAX];

        // Build the graph
        for (int i = 0; i < m; i++) {
            adj[source[i]][dest[i]] = 1;
            outDeg[source[i]]++;
            inDeg[dest[i]]++;
        }

        // Find and return the minimum number of edges required
        return findSCCs(adj, n, inDeg, outDeg);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of intersections and one-way streets
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Read the edges
        int[] source = new int[MAX];
        int[] dest = new int[MAX];
        for (int i = 0; i < m; i++) {
            source[i] = scanner.nextInt();
            dest[i] = scanner.nextInt();
        }

        // Find the minimum number of additional streets required
        System.out.println(findMinimumEdges(source, dest, n, m));

        scanner.close();
    }
}


// 🌿 Problem Statement: Counting Plant Clusters

// Selena, a biologist, is studying a 2D habitat map where each cell represents a species of plant. 
// Her goal is to identify and count the number of **distinct plant clusters** present in the habitat.

// A **plant cluster** is defined as a group of **adjacent** cells (connected **horizontally or vertically**) 
// that contain the **same species** of plant. 

// Write a program to help Selena determine how many such distinct clusters exist in the given habitat map.

// 📥 Input Format:
// • The first line contains an integer `n`, representing the number of rows in the habitat map.
// • The second line contains an integer `m`, representing the number of columns in the habitat map.
// • The next `n` lines each contain `m` characters. Each character represents a species of plant at that cell (`mat[i][j]`).

// 📤 Output Format:
// • Print a single integer — the number of distinct plant clusters in the habitat map.

// 🔒 Constraints:
// • 1 ≤ n, m ≤ 10
// • mat[i][j] is a lowercase English letter ('a' to 'z')

// 🧪 Sample Test Case 1:
// Input:
// 4
// 4
// aabb
// aabb
// bbaa
// bbaa

// Output:
// 4

// 🧪 Sample Test Case 2:
// Input:
// 4
// 2
// ab
// cb
// cc
// aa

// Output:
// 4

// 🧠 Explanation:
// In Test Case 1, there are 4 clusters:
// - Cluster 1: 'a' cells at top-left
// - Cluster 2: 'b' cells at top-right
// - Cluster 3: 'b' cells at bottom-left
// - Cluster 4: 'a' cells at bottom-right


import java.util.Scanner;

class TerrainAnalysis {
    
    static boolean[][] visited;
    
    // Function to check if the cell is safe to visit
    static boolean isSafe(char[][] grid, int row, int col, char c, int n, int m) {
        return (row >= 0 && row < n) && (col >= 0 && col < m) && (grid[row][col] == c && !visited[row][col]);
    }
    
    // Depth First Search to visit connected cells
    static void DFS(char[][] grid, int row, int col, char c, int n, int m) {
        // Direction arrays for moving up, down, left, right
        int[] rowNbr = {-1, 1, 0, 0};
        int[] colNbr = {0, 0, 1, -1};
        
        visited[row][col] = true;
        
        // Recursively visit all adjacent cells
        for (int k = 0; k < 4; ++k) {
            if (isSafe(grid, row + rowNbr[k], col + colNbr[k], c, n, m)) {
                DFS(grid, row + rowNbr[k], col + colNbr[k], c, n, m);
            }
        }
    }
    
    // Function to count the number of connected components
    static int connectedComponents(char[][] grid, int n, int m) {
        int connectedComp = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    char c = grid[i][j];
                    DFS(grid, i, j, c, n, m);
                    connectedComp++;
                }
            }
        }
        
        return connectedComp;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Reading input
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        char[][] grid = new char[n][m];
        
        // Initialize the grid with input values
        for (int i = 0; i < n; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }
        
        // Initialize visited array
        visited = new boolean[n][m];
        
        // Output the result
        System.out.println(connectedComponents(grid, n, m));
        
        scanner.close();
    }
}


// 📦 Problem Statement: Identifying Strongly Connected Warehouses

// Emily, a logistics coordinator, is working on optimizing the flow of goods in a distribution network.
// The network consists of several warehouses, connected by **one-way paths** (directed edges).

// To optimize routing, Emily needs to identify groups of warehouses that can **reach each other in both directions**.
// These groups are known as **Strongly Connected Components (SCCs)**.

// Write a program that helps Emily find all SCCs in the warehouse network.

// 📥 Input Format:
// • The first line contains an integer `V` — the number of warehouses (vertices).
// • The second line contains an integer `E` — the number of one-way paths (edges).
// • The next `E` lines each contain two integers `u` and `v` representing a one-way path from warehouse `u` to warehouse `v`.

// 📤 Output Format:
// • Print each SCC on a new line.
// • Each line should contain a space-separated list of warehouse numbers that belong to the same SCC.
// • Warehouses in each SCC can be printed in any order.

// 🔒 Constraints:
// • 1 ≤ V ≤ 10  
// • 1 ≤ E ≤ 10

// 🧪 Sample Test Case 1:
// Input:
// 2
// 2
// 1 2
// 2 1

// Output:
// 1 2 

// 🧪 Sample Test Case 2:
// Input:
// 3
// 3
// 1 2
// 2 3
// 3 1

// Output:
// 1 2 3

import java.util.Scanner;

public class Main {

    static final int MAX = 100;

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

    static boolean isPath(int src, int des, int[][] adj, int n) {
        int[] vis = new int[MAX];
        return dfs(src, des, adj, vis, n);
    }

    static void findSCC(int n, int[][] edges, int edgeCount) {
        int[][] adj = new int[MAX][MAX];
        int[] is_scc = new int[MAX];

        for (int i = 0; i < edgeCount; i++) {
            adj[edges[i][0]][edges[i][1]] = 1;
        }

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

                for (int k = 0; k < scc_size; k++) {
                    System.out.print(scc[k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();  
        int E = scanner.nextInt();  

        int[][] edges = new int[MAX][2];
        for (int i = 0; i < E; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        findSCC(V, edges, E);
        scanner.close();
    }
}
