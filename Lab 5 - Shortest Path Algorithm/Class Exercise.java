/*
 * Problem Statement:
 *
 * A traveler needs to find the shortest path between every pair of cities in a road network. 
 * The cities are connected by roads, each with a specific travel cost. 
 * To do this, the traveler uses the Floyd-Warshall Algorithm to calculate the shortest distances between every pair of cities in a given edge-weighted undirected graph.
 *
 * Example1:
 *
 * Input:
 * 4
 * 3
 * 0 1 2
 * 1 2 3
 * 2 3 4
 *
 * Output:
 * Original matrix
 * 0 2 INF INF 
 * 2 0 3 INF 
 * INF 3 0 4 
 * INF INF 4 0 
 *
 * Shortest path matrix
 * 0 2 5 9 
 * 2 0 3 7 
 * 5 3 0 4 
 * 9 7 4 0
 *
 * Explanation:
 * Given: 4 nodes (0, 1, 2, 3) and 3 edges: 0 → 1 (weight 2), 1 → 2 (weight 3), 2 → 3 (weight 4)
 *
 * Any node pair without a direct edge is considered INF (unreachable initially).
 * Step 1: Construct Initial Graph (Original Matrix)
 * 0   2   INF INF
 * 2   0   3   INF
 * INF 3   0   4
 * INF INF 4   0
 *
 * Diagonal elements (self to self) are 0.
 * If no direct path exists, it's INF.
 *
 * Step 2: Running Floyd-Warshall Algorithm
 * Using node 0 as an intermediate: No updates since it only connects to node 1.
 * Using node 1 as an intermediate: 0 → 2 via 1: 0 → 1 (2) + 1 → 2 (3) = 5
 * 2 → 0 also updates to 5.
 * Using node 2 as an intermediate: 0 → 3 via 2: 0 → 2 (5) + 2 → 3 (4) = 9
 * 1 → 3 via 2: 1 → 2 (3) + 2 → 3 (4) = 7
 * Using node 3 as an intermediate: No further updates.
 *
 * Step 3: Final Shortest Path Matrix
 * 0 2 5 9 
 * 2 0 3 7 
 * 5 3 0 4 
 * 9 7 4 0
 *
 * Input format:
 * The first line contains an integer V representing the number of cities (vertices) in the network.
 * The second line contains an integer E representing the number of roads (edges) in the network.
 * The next E lines each contain three space-separated integers u, v, w, where u and v represent the cities connected by a road, and w represents the travel cost (weight) of the road.
 *
 * Output format:
 * The first line of output prints the original matrix, where each entry represents the direct travel cost between two cities. If there is no direct road between two cities, represent the cost as INF.
 * Then, print the shortest path matrix, where each entry represents the shortest travel cost between every pair of cities, computed using the Floyd-Warshall algorithm.
 *
 * Code constraints:
 * The given test cases fall under the following specifications:
 * - 1 ≤ V ≤ 1000
 * - 0 ≤ E ≤ 10,000
 * - 0 ≤ u, v < V
 * - -1000 ≤ w ≤ 1000
 *
 * Sample test cases:
 * Input 1:
 * 4
 * 3
 * 0 1 2
 * 1 2 3
 * 2 3 4
 * Output 1:
 * Original matrix
 * 0 2 INF INF 
 * 2 0 3 INF 
 * INF 3 0 4 
 * INF INF 4 0 
 *
 * Shortest path matrix
 * 0 2 5 9 
 * 2 0 3 7 
 * 5 3 0 4 
 * 9 7 4 0
 *
 * Input 2:
 * 5
 * 5
 * 0 1 2
 * 0 2 7
 * 1 2 3
 * 2 3 4
 * 3 4 5
 * Output 2:
 * Original matrix
 * 0 2 7 INF INF 
 * 2 0 3 INF INF 
 * 7 3 0 4 INF 
 * INF INF 4 0 5 
 * INF INF INF 5 0 
 *
 * Shortest path matrix
 * 0 2 5 9 14 
 * 2 0 3 7 12 
 * 5 3 0 4 9 
 * 9 7 4 0 5 
 * 14 12 9 5 0
 */



// You are using Java
import java.util.Scanner;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input
        int V = scanner.nextInt(); // Number of vertices
        int E = scanner.nextInt(); // Number of edges
        
        // Initialize distance matrix with INF
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    graph[i][j] = 0; // Distance from a vertex to itself is 0
                } else {
                    graph[i][j] = INF; // Initialize all other distances as infinite
                }
            }
        }
        
        // Add edges to the graph
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            
            // Since the graph is undirected
            graph[u][v] = w;
            graph[v][u] = w;
        }
        
        scanner.close();
        
        // Print original matrix
        System.out.println("Original matrix");
        printMatrix(graph, V);
        
        // Apply Floyd-Warshall algorithm
        int[][] shortestPaths = floydWarshall(graph, V);
        
        // Print shortest path matrix
        System.out.println("\nShortest path matrix");
        printMatrix(shortestPaths, V);
    }
    
    // Implementation of Floyd-Warshall algorithm
    private static int[][] floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];
        
        // Initialize the distance matrix with the same values as the input graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        
        // Consider each vertex as an intermediate vertex
        for (int k = 0; k < V; k++) {
            // Consider all pairs of vertices (i, j)
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j,
                    // then update the value of dist[i][j]
                    if (dist[i][k] != INF && dist[k][j] != INF && 
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        return dist;
    }
    
    // Helper method to print the matrix
    private static void printMatrix(int[][] matrix, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}




/*
 * Problem Statement:
 *
 * The Floyd-Warshall Algorithm is for solving all pairs of shortest-path problems. 
 * The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. 
 *
 * Input format:
 * The first line of input represents the size N.
 * The next N*N Matrix represents the distance.
 * Note: The distance between one city and itself will be 0, and the distance will be 999 if there is no direct edge between the two cities.
 *
 * Output format:
 * The output prints the shortest distance between every pair of cities.
 * If there is no shortest path between two cities, then print INF.
 *
 * Sample test cases:
 * Input 1:
 * 4
 * 0 5 999 10
 * 999 0 3 999
 * 999 999 0 1
 * 999 999 999 0
 * Output 1:
 * 0 5 8 9 
 * INF 0 3 4 
 * INF INF 0 1 
 * INF INF INF 0
 *
 * Explanation:
 * Given a graph with 4 cities, where:
 * - 0 → 1 with weight 5
 * - 1 → 2 with weight 3
 * - 2 → 3 with weight 1
 * For the first matrix:
 * - If there is no direct edge between two cities, the distance is represented as 999 (INF).
 * The algorithm will then compute the shortest path between every pair of cities using the Floyd-Warshall algorithm.
 *
 * Output:
 * The shortest paths matrix:
 * - City 0 to City 1 has a direct distance of 5.
 * - City 0 to City 2 can be reached through City 1 with a distance of 8 (5 + 3).
 * - City 0 to City 3 can be reached through City 2 with a distance of 9 (5 + 3 + 1).
 *
 * Similarly, the shortest paths for all pairs will be computed.
 */




import java.util.Scanner;

public class Main {
    private static final int INF = 999;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input size
        int N = scanner.nextInt();
        
        // Initialize distance matrix
        int[][] dist = new int[N][N];
        
        // Read the distance matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = scanner.nextInt();
            }
        }
        
        scanner.close();
        
        // Apply Floyd-Warshall algorithm
        floydWarshall(dist, N);
        
        // Print the result
        printMatrix(dist, N);
    }
    
    // Implementation of Floyd-Warshall algorithm
    private static void floydWarshall(int[][] dist, int N) {
        // Consider each vertex as an intermediate vertex
        for (int k = 0; k < N; k++) {
            // Consider all pairs of vertices (i, j)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // If vertex k is on the shortest path from i to j,
                    // then update the value of dist[i][j]
                    if (dist[i][k] != INF && dist[k][j] != INF && 
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
    
    // Helper method to print the matrix
    private static void printMatrix(int[][] matrix, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF");
                } else {
                    System.out.print(matrix[i][j]);
                }
                
                // Add space after each element except the last one in a row
                if (j < N - 1) {
                    System.out.print(" ");
                }
            }
            // Add newline after each row
            System.out.println();
        }
    }
}


