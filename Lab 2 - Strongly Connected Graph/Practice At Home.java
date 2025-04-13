// Problem Statement

// Given an unweighted directed graph G represented as a path matrix, the task is to determine 
// whether the graph is strongly connected, unilaterally connected, or weakly connected.


// Examples:

// Strongly Connected Graph:
// A graph where there is a directed path between every pair of nodes.

// Unilaterally Connected Graph:
// A graph where for each pair of nodes (i, j), there exists at least one directed path 
// (either i -> j or j -> i).

// Weakly Connected Graph:
// A graph where if we ignore the direction of the edges, all nodes are reachable from each other.


/**
 * Input format:
 * The first line contains an integer N, representing the number of nodes.
 * The next N lines contain N space-separated integers representing the path matrix of the graph.
 * Path matrix elements are either 0 or 1.
 * 
 * Output format:
 * The output will be a single line containing one of the following:
 * "Strongly Connected", "Unilaterally Connected", or "Weakly Connected".
 */

// Code constraints:
// 1 ≤ N ≤ 6
// Path matrix elements are either 0 or 1.

// Sample Test Cases:
// Input 1:
// 3
// 1 1 1
// 1 1 1
// 1 1 1
// Output 1:
// Strongly Connected

// Input 2:
// 3
// 0 1 1
// 0 0 1
// 0 0 0
// Output 2:
// Unilaterally Connected

// Input 3:
// 3
// 0 1 0
// 0 0 0
// 0 1 0
// Output 3:
// Weakly Connected

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of nodes
        int n = scanner.nextInt();

        // Given Path Matrix
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                graph[i][j] = scanner.nextInt();
            }
        }

        boolean strongly = true;

        // Traverse the path matrix
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // If all the elements are not equal then the graph is not strongly connected
                if (graph[i][j] != graph[j][i]) {
                    strongly = false;
                    break;
                }
            }
            // Break out of the loop if false
            if (!strongly) {
                break;
            }
        }

        // If true then print strongly connected and return
        if (strongly) {
            System.out.println("Strongly Connected");
            return;
        }

        // Check whether the graph is unilaterally connected by checking Upper Triangle element
        boolean uppertri = true;

        // Traverse the path matrix
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // If uppertriangle elements are 0 then break out of the loop and check the elements
                // of lowertriangle matrix
                if (i > j && graph[i][j] == 0) {
                    uppertri = false;
                    break;
                }
            }
            // Break out of the loop if false
            if (!uppertri) {
                break;
            }
        }

        // If true then print unilaterally connected and return
        if (uppertri) {
            System.out.println("Unilaterally Connected");
            return;
        }

        // Check lowertraingle elements
        boolean lowertri = true;

        // Traverse the path matrix
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // If lowertraingle elements are 0 then break cause 1's are expected
                if (i < j && graph[i][j] == 0) {
                    lowertri = false;
                    break;
                }
            }
            // Break out of the loop if false
            if (!lowertri) {
                break;
            }
        }

        // If true then print unilaterally connected and return
        if (lowertri) {
            System.out.println("Unilaterally Connected");
            return;
        }

        // If elements are in random order unsynchronized then print weakly connected and return
        else {
            System.out.println("Weakly Connected");
        }

        scanner.close();
    }
}

// Problem Statement
//
// Olivia, a software engineer, is analyzing a directed graph of interconnected systems. 
// She needs to identify Strongly Connected Components (SCCs) using Tarjan’s Algorithm, 
// which efficiently finds all SCCs in a graph. An SCC is a group of vertices where 
// every vertex can reach every other vertex in the same SCC.
//
// Help Olivia determine and print all SCCs in the given directed graph.
//
// Input format:
// The first line of input consists of two integers V and E, where V is the number of vertices 
// and E is the number of directed edges.
//
// The next E lines each contain two integers u and v, representing a directed edge from u to v.
//
// Output format:
// The output prints each Strongly Connected Component (SCC) in a separate line.
//
// Refer to the sample output for formatting specifications.
//
// Code constraints:
// The given test cases fall under the following specifications:
//
// 1 ≤ V ≤ 10
// 0 ≤ E ≤ 20
// 0 ≤ u, v < V
//
// Sample test cases:
// Input 1:
// 5 5
// 1 0
// 0 2
// 2 1
// 0 3
// 3 4
// Output 1:
// 4
// 3
// 1 2 0
//
// Input 2:
// 4 3
// 0 1
// 1 2
// 2 3
// Output 2:
// 3
// 2
// 1
// 0



import java.util.*;

 class StronglyConnectedComponents {

    static final int MAX_NODES = 1000;
    static final int NIL = -1;
    static LinkedList<Integer>[] adjList = new LinkedList[MAX_NODES];
    static int[] disc = new int[MAX_NODES], low = new int[MAX_NODES], stack = new int[MAX_NODES];
    static boolean[] stackMember = new boolean[MAX_NODES];
    static int time = 0, top = -1;
    static int V, E;

    // Add an edge to the adjacency list
    static void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Utility function to perform DFS and find SCC
    static void SCCUtil(int u) {
        disc[u] = low[u] = ++time;
        stack[++top] = u;
        stackMember[u] = true;

        for (int v : adjList[u]) {
            if (disc[v] == NIL) {
                SCCUtil(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            while (stack[top] != u) {
                System.out.print(stack[top] + " ");
                stackMember[stack[top]] = false;
                top--;
            }
            System.out.println(stack[top]);
            stackMember[stack[top]] = false;
            top--;
        }
    }

    // Find and print all SCCs
    static void SCC() {
        // Initialize arrays
        Arrays.fill(disc, NIL);
        Arrays.fill(low, NIL);
        Arrays.fill(stackMember, false);

        for (int i = 0; i < V; i++) {
            if (disc[i] == NIL) {
                SCCUtil(i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }

        // Input edges
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
        }

        // Find and print SCCs
        SCC();
        sc.close();
    }
}



// Problem Statement
//
// The cities in a country are connected as a graph. There should be a path between all pairs of cities. 
// We have to find the maximal strongly connected cities. Given a set of vertices and edges, 
// write a program to identify the strongly connected components in a graph.
//
// Strongly connected components in a graph are sets of vertices where each vertex in the set 
// can be reached from every other vertex in the same set by following directed edges.
//
// Input format:
// The first line consists of the number of edges n.
//
// The next n lines consist of two integers: a and b representing directed edges from vertex a to vertex b.
//
// Output format:
// The output prints the strongly connected components in the graph, each on a separate line.
//
// The components are printed as space-separated integers representing the vertices in each component.
//
// Refer to the sample output for the formatting specifications.
//
// Code constraints:
// The given test cases fall under the following specifications:
//
// 0 ≤ a, b < n
//
// Sample test cases:
// Input 1:
// 5
// 1 0
// 0 2
// 2 1
// 0 3
// 3 4
// Output 1:
// 0 1 2 
// 3 
// 4


import java.util.*;

class StronglyConnectedComponents {

    static class Graph {
        int V;
        List<Integer>[] adj;

        @SuppressWarnings("unchecked")
        Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // Transpose the graph
        Graph getTranspose() {
            Graph transpose = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (int adjVertex : adj[v]) {
                    transpose.addEdge(adjVertex, v);
                }
            }
            return transpose;
        }

        // Perform DFS
        void DFS(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");

            for (int adjVertex : adj[v]) {
                if (!visited[adjVertex]) {
                    DFS(adjVertex, visited);
                }
            }
        }
    }

    // Utility function to fill the order of vertices to process later
    static void fillOrder(Graph graph, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int adjVertex : graph.adj[v]) {
            if (!visited[adjVertex]) {
                fillOrder(graph, adjVertex, visited, stack);
            }
        }
        stack.push(v);  // Add to stack after visiting all the adjacent vertices
    }

    // Function to print SCCs
    static void printSCCs(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.V];

        // Fill vertices in the stack according to their finishing times
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                fillOrder(graph, i, visited, stack);
            }
        }

        // Get the transposed graph
        Graph transpose = graph.getTranspose();

        // Reset visited array for second DFS
        Arrays.fill(visited, false);

        // Process all vertices in order defined by stack
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                // Perform DFS on the transposed graph
                transpose.DFS(v, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of vertices and edges
        int n = sc.nextInt();
        Graph graph = new Graph(n);

        // Read the edges
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.addEdge(a, b);
        }

        // Print SCCs
        printSCCs(graph);
    }
}


// Problem Statement
//
// Sophia, a network architect, is given a directed network of n routers and m one-way connections. 
// She needs to determine the minimum number of additional connections required to make the entire network strongly connected, 
// ensuring every router can reach every other router.
//
// Help Sophia find the minimum edges needed to achieve strong connectivity.
//
// Note: In a directed graph, a Strongly Connected Component is a subset of vertices where every vertex 
// in the subset is reachable from every other vertex in the same subset by traversing the directed edges.
//
// Input format:
// The first line of input consists of an integer n, representing the number of routers (vertices).
//
// The second line consists of an integer m, representing the number of directed connections (edges).
//
// The next m lines each contain two integers u and v, representing a directed connection from router u to router v.
//
// Output format:
// The output prints a single integer representing the minimum number of additional edges required to make the graph strongly connected.
//
// Refer to the sample output for the formatting specifications.
//
// Code constraints:
// The given test cases fall under the following specifications:
//
// 1 ≤ n ≤ 10
//
// 1 ≤ m ≤ 10
//
// 0 ≤ u, v ≤ n
//
// Sample test cases:
// Input 1:
// 5
// 5
// 1 2
// 3 2
// 1 3
// 3 4
// 4 5
// Output 1:
// 2
//
// Input 2:
// 3
// 2
// 1 2  
// 2 3
// Output 2:
// 1


import java.util.Scanner;

public class Main {

    static final int MAX = 100;

    // Depth-First Search (DFS)
    static void dfs(int v, int[][] adj, int[] vis, int n) {
        vis[v] = 1;
        for (int i = 1; i <= n; i++) {
            if (adj[v][i] == 1 && vis[i] == 0) {
                dfs(i, adj, vis, n);
            }
        }
    }

    // Function to transpose the graph
    static void transposeGraph(int[][] adj, int[][] trans, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trans[j][i] = adj[i][j];
            }
        }
    }

    // Function to find SCCs and return the minimum number of edges
    static int findSCCs(int[][] adj, int n, int[] inDeg, int[] outDeg) {
        int[] vis = new int[MAX];
        int[] component = new int[MAX];
        int sccCount = 0;

        // Perform DFS to assign components
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                dfs(i, adj, vis, n);
                component[i] = ++sccCount;
            }
        }

        // Transpose the graph
        int[][] trans = new int[MAX][MAX];
        transposeGraph(adj, trans, n);

        // Reset vis array for second DFS
        for (int i = 1; i <= n; i++) {
            vis[i] = 0;
        }

        // Perform DFS on transposed graph
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                dfs(i, trans, vis, n);
            }
        }

        int sources = 0, sinks = 0;
        // Count sources and sinks
        for (int i = 1; i <= n; i++) {
            if (inDeg[i] == 0) sources++;
            if (outDeg[i] == 0) sinks++;
        }

        // The number of new streets required is the max of sources and sinks
        return Math.max(sources, sinks);
    }

    // Function to calculate the minimum number of edges
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


