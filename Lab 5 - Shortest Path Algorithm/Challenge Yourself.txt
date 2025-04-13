/*
 * Problem Statement:
 * Raghav is working on mastering graph algorithms, and his mentor has presented a challenge involving a directed graph 
 * characterized by 4 vertices and 4 edges. Each edge in the graph has a specific weight representing the distance between 
 * two vertices. Raghav must implement the Floyd-Warshall algorithm and reconstruct the shortest paths linking the vertices.
 * 
 * Input format:
 * The input consists of four lines, each containing four space-separated integers representing the adjacency matrix of the graph.
 * Each integer represents the weight of the edge between two vertices.
 * Use 999 to represent infinity if there is no direct edge between two vertices.
 * 
 * Output format:
 * The output consists of two main parts:
 * 1. The 4x4 matrix representing the shortest distance between all pairs of vertices.
 * 2. The shortest paths between every pair of vertices, where each shortest path is printed in the format "Shortest path from node u to node v: ".
 * 
 * Code constraints:
 * - 0 ≤ Weight of edge ≤ 999
 * 
 * Sample Test Case:
 * Input 1:
 * 0 3 999 7
 * 8 0 2 999
 * 5 999 0 1
 * 2 999 999 0
 * Output 1:
 * 0 3 5 6 
 * 5 0 2 3 
 * 3 6 0 1 
 * 2 5 7 0 
 * Shortest paths between every pair of vertices:
 * Shortest path from node 0 to node 1: 0 -> 1
 * Shortest path from node 0 to node 2: 0 -> 1 -> 2
 * Shortest path from node 0 to node 3: 0 -> 1 -> 2 -> 3
 * Shortest path from node 1 to node 0: 1 -> 2 -> 3 -> 0
 * Shortest path from node 1 to node 2: 1 -> 2
 * Shortest path from node 1 to node 3: 1 -> 2 -> 3
 * Shortest path from node 2 to node 0: 2 -> 3 -> 0
 * Shortest path from node 2 to node 1: 2 -> 3 -> 0 -> 1
 * Shortest path from node 2 to node 3: 2 -> 3
 * Shortest path from node 3 to node 0: 3 -> 0
 * Shortest path from node 3 to node 1: 3 -> 0 -> 1
 * Shortest path from node 3 to node 2: 3 -> 0 -> 1 -> 2
 * 
 * Input 2:
 * 0 3 999 4
 * 9 5 0 2
 * 5 999 0  1
 * 2 999 999 0
 * Output 2:
 * 0 3 3 4 
 * 3 5 0 1 
 * 3 6 0 1 
 * 2 5 5 0 
 * Shortest paths between every pair of vertices:
 * Shortest path from node 0 to node 1: 0 -> 1
 * Shortest path from node 0 to node 2: 0 -> 1 -> 2
 * Shortest path from node 0 to node 3: 0 -> 3
 * Shortest path from node 1 to node 0: 1 -> 2 -> 3 -> 0
 * Shortest path from node 1 to node 2: 1 -> 2
 * Shortest path from node 1 to node 3: 1 -> 2 -> 3
 * Shortest path from node 2 to node 0: 2 -> 3 -> 0
 * Shortest path from node 2 to node 1: 2 -> 3 -> 0 -> 1
 * Shortest path from node 2 to node 3: 2 -> 3
 * Shortest path from node 3 to node 0: 3 -> 0
 * Shortest path from node 3 to node 1: 3 -> 0 -> 1
 * Shortest path from node 3 to node 2: 3 -> 0 -> 1 -> 2
 */


import java.util.Scanner;

class FloydWarshallAlgorithm {

    static final int V = 4;
    static final int INF = 99999;

    // Function to print the solution matrix and shortest paths
    static void printSolution(int[][] dist, int[][] next) {
        // Print the distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        // Print the shortest paths
        System.out.println("Shortest paths between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && next[i][j] != -1) {
                    System.out.print("Shortest path from node " + i + " to node " + j + ": " + i);
                    int nextNode = next[i][j];
                    while (nextNode != j) {
                        System.out.print(" -> " + nextNode);
                        nextNode = next[nextNode][j];
                    }
                    System.out.println(" -> " + j);
                }
            }
        }
    }

    // Function to perform Floyd-Warshall algorithm
    static void floydWarshall(int[][] graph) {
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Initialize dist[][] and next[][]
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Apply Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        printSolution(dist, next);
    }

    // Driver program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] graph = new int[V][V];

        // Read the adjacency matrix of the graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        floydWarshall(graph);
        scanner.close();
    }
}



/*
 * Problem Statement:
 *
 * Venu, being passionate about algorithms, decides to implement the Floyd-Warshall algorithm to find the transitive closure of a directed graph.
 *
 * The graph is represented by its adjacency matrix, where the value 999 represents the absence of a direct edge between two vertices. 
 * Venu wants to write a program to find the transitive closure of this graph and print the resulting matrix.
 *
 * Input format:
 * The first line contains two space-separated integers n and e, denoting the number of vertices and the number of edges in the graph.
 *
 * Each of the next e lines contains three space-separated integers u, v, and w, where:
 *   - u and v represent the vertices of the directed edge.
 *   - w represents the weight of the edge.
 *
 * Output format:
 * The output displays the integers, representing the transitive closure of the given graph in the form of an n x n matrix.
 *
 * Note:
 * - Rows and columns should be separated by tab space.
 *
 * Code constraints:
 * - 1 ≤ n ≤ 10 (number of vertices)
 * - 1 ≤ e ≤ 10 (number of edges)
 * - 1 ≤ u, v ≤ n (vertex numbers)
 * - 1 ≤ w ≤ 999 (weight of edge)
 *
 * Sample Test Cases:
 * 
 * Input 1:
 * 3 3
 * 1 2 3
 * 2 3 1
 * 3 1 4
 * 
 * Output 1:
 * Transitive closure
 * 0	3	4
 * 5	0	1
 * 4	7	0
 *
 * Input 2:
 * 3 3
 * 1 2 5
 * 2 3 7
 * 3 1 4
 * 
 * Output 2:
 * Transitive closure
 * 0	5	12
 * 11	0	7
 * 4	9	0
 */


import java.util.Scanner;

class FloydsAlgorithm {

    static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    static void floyds(int[][] p, int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        p[i][j] = 0;
                    } else {
                        p[i][j] = min(p[i][j], p[i][k] + p[k][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] p = new int[11][11]; // Size 11 to handle 1-based indexing easily
        int w, n, e, u, v;

        n = scanner.nextInt();
        e = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                p[i][j] = 999;
            }
        }

        for (int i = 1; i <= e; i++) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextInt();
            p[u][v] = w;
        }

        floyds(p, n);

        System.out.println("Transitive closure");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(p[i][j] + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}


/*
 * Problem Statement:
 * 
 * In a city, there are several locations connected by roads. Each road has a certain distance. 
 * You are tasked with finding the shortest distances between all pairs of locations in the city 
 * using the Floyd-Warshall algorithm.
 * 
 * Write a program that calculates the shortest path between all pairs of locations in the city.
 * 
 * Example Input:
 * 
 * 4
 * 4
 * 0 1 3
 * 1 2 1
 * 2 3 1
 * 0 2 5
 * 
 * Output:
 * 
 * 0 3 4 5 
 * 3 0 1 2 
 * 4 1 0 1 
 * 5 2 1 0
 * 
 * Explanation:
 * 
 * The city has 4 locations (0 to 3).
 * There are 4 roads connecting the locations.
 * The roads and their distances are:
 * 
 * 0 → 1 with distance 3
 * 1 → 2 with distance 1
 * 2 → 3 with distance 1
 * 0 → 2 with distance 5
 * 
 * Initially, the distance matrix is set to infinity (INF) except for direct roads and diagonal values (0).
 * The Floyd-Warshall algorithm updates the matrix by checking intermediate paths.
 * The shortest path from 0 to 2 (via 1) is updated to 4 (0 → 1 → 2) instead of 5.
 * The shortest path from 0 to 3 is 5 (0 → 1 → 2 → 3).
 * 
 * The final distance matrix is printed, showing the shortest distances.
 * 
 * 0 3 4 5
 * 3 0 1 2
 * 4 1 0 1
 * 5 2 1 0
 * 
 * Input format:
 * 
 * The first line contains an integer N, representing the number of locations in the city.
 * The second line contains an integer M, representing the number of roads in the city.
 * The next M lines each contain three space-separated integers: u, v, w where:
 * 
 * u and v represent two locations connected by a road.
 * w represents the distance between u and v.
 * 
 * Output format:
 * 
 * The output prints a matrix, where the value at the i-th row and j-th column represents the shortest distance 
 * between location i and location j.
 * 
 * Code constraints:
 * 
 * In this scenario, the given test cases will fall under the following constraints:
 * 2 ≤ N ≤ 5
 * 1 ≤ M ≤ 10
 * 0 ≤ w ≤ 100
 * 
 * Sample Test Cases:
 * 
 * Input 1:
 * 4
 * 4
 * 0 1 3
 * 1 2 1
 * 2 3 1
 * 0 2 5
 * 
 * Output 1:
 * 0 3 4 5 
 * 3 0 1 2 
 * 4 1 0 1 
 * 5 2 1 0
 * 
 * Input 2:
 * 3
 * 3
 * 0 1 2
 * 1 2 3
 * 0 2 6
 * 
 * Output 2:
 * 0 2 5 
 * 2 0 3 
 * 5 3 0
 */



import java.util.Scanner;

interface GraphOperations {
    void floydWarshall();
    void printSolution();
}

class FloydWarshall implements GraphOperations {
    private int[][] dist;
    private int N;
    private static final int INF = Integer.MAX_VALUE;

    public FloydWarshall(int N, int M, Scanner sc) {
        this.N = N;
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            dist[u][v] = dist[v][u] = Math.min(dist[u][v], w);
        }
    }

    @Override
    public void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    @Override
    public void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        FloydWarshall graph = new FloydWarshall(N, M, sc);
        graph.floydWarshall();
        graph.printSolution();
    }
}


