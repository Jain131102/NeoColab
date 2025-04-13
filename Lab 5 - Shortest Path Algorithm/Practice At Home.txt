/*
 * Problem Statement:
 *
 * Sarah has been tasked with developing a program to determine the shortest paths among nodes
 * in a directed network represented as a graph containing nodes and weighted edges.
 *
 * Her goal is to implement the Floyd-Warshall algorithm to compute the shortest path matrix
 * for this directed network.
 *
 * Input format:
 *
 * The first line of input consists of an integer n, representing the number of nodes in the network.
 *
 * The next n lines contain space-separated integers, representing the adjacency matrix 'graph' of the network.
 * Each integer is the weight of the edge from node i to node j.
 * 
 * Output format:
 *
 * The output prints the shortest path matrix as 'n x n' integers, where each element graph[i][j] represents 
 * the shortest distance from node 'i' to node 'j'.
 * 
 * Print each row of the matrix on a new line, with elements separated by a space.
 *
 * Code constraints:
 * 
 * In this scenario, the given test cases will fall under the following constraints:
 * 
 * 1 ≤ n ≤ 100
 * 0 ≤ weight of edge ≤ 999
 * graph[i][i] = 0
 *
 * Sample Test Cases:
 * 
 * Input 1:
 * 4
 * 0 3 999 4
 * 8 0 2 999
 * 5 999 0 1
 * 2 999 999 0
 *
 * Output 1:
 * 0 3 5 4 
 * 5 0 2 3 
 * 3 6 0 1 
 * 2 5 7 0
 *
 * Input 2:
 * 3
 * 0 1 2
 * 3 0 4
 * 5 6 0
 *
 * Output 2:
 * 0 1 2 
 * 3 0 4 
 * 5 6 0
 *
 * Input 3:
 * 4
 * 0 5 999 10
 * 999 0 3 999
 * 999 999 0 1
 * 999 999 999 0
 *
 * Output 3:
 * 0 5 8 9 
 * 999 0 3 4 
 * 999 999 0 1 
 * 999 999 999 0
 */



import java.util.Scanner;

class FloydWarshallAlgorithm {

    public static void floydWarshall(int[][] graph, int n) {
        int i, j, k;
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        
        // Initialize the graph with maximum values and 0 on the diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 100;
                }
            }
        }

        // Input the edges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        floydWarshall(graph, n);

        // Output the shortest paths
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}



/*
 * Problem Statement:
 * 
 * Rajesh is on a quest to master graph algorithms, and his mentor has presented him with an intriguing challenge. 
 * He is given a directed graph consisting of four vertices and four edges, where each edge has a specific weight 
 * representing the distance between two vertices.
 *
 * His task is twofold: 
 *   - First, he must implement the Floyd-Warshall algorithm to compute the shortest distances between all pairs of vertices. 
 *   - Second, he needs to reconstruct the shortest paths that connect these vertices. Assist Rajesh in accomplishing this challenge.
 *
 * Input format:
 * 
 * The input consists of four lines, each containing four space-separated integers representing the adjacency matrix of the graph.
 * 
 * Each integer represents the weight of the edge between two vertices.
 * Use 999 to represent infinity if there is no direct edge between two vertices.
 * 
 * Output format:
 * 
 * The output prints the 4x4 matrix representing the shortest distance.
 * 
 * The next line prints "Shortest paths between every pair of vertices:".
 * 
 * The next line prints the shortest paths between every pair of vertices. For each pair of vertices (u, v), in the format 
 * of "Shortest paths from node u to node v: ", separated by arrows "->".
 * 
 * Refer to the sample output for the formatting specifications.
 * 
 * Code constraints:
 * 
 * In this scenario, the given test cases will fall under the following constraints:
 * 
 * 0 ≤ Weight of edge ≤ 999
 *
 * Sample Test Cases:
 * 
 * Input 1:
 * 0 3 999 7
 * 8 0 2 999
 * 5 999 0 1
 * 2 999 999 0
 *
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
 *
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
 * Bhargavi is a dedicated network engineer working on optimizing communication routes within a computer network. 
 * She is facing the challenge of finding the shortest paths between different nodes in a network. 
 * Bhargavi knows about the Floyd-Warshall algorithm, which is an efficient way to solve this problem.
 *
 * Help her write a program that takes input data representing a network and calculates the shortest paths between 
 * all pairs of nodes using the Floyd-Warshall algorithm.
 *
 * The program should display both the input matrix and the transitive closure matrix.
 * 
 * Input format:
 * The first line contains an integer n, representing the number of nodes, and an integer e, representing the number 
 * of edges separated by a space.
 * 
 * The next e lines contain three space-separated integers u, v, and w, where u and v are the nodes connected by an 
 * edge with a weight w.
 * 
 * Note: These edges are directed, and there can be multiple edges between the same pair of nodes.
 * 
 * Output format:
 * The output displays the following format:
 * 
 * The matrix of input data shows the initial edge weights between nodes. This matrix should be displayed with rows 
 * and columns representing nodes and the elements of the matrix indicating the edge weights.
 * 
 * The transitive closure matrix represents the shortest paths between all pairs of nodes. This matrix should also 
 * be displayed with rows and columns representing nodes, and the elements of the matrix indicating the shortest 
 * path lengths.
 * 
 * Note: Rows and columns should be separated by tab space.
 * 
 * Code constraints:
 * 
 * The given test cases will fall under the following specifications:
 * 
 * 1 ≤ n ≤ 10
 * 1 ≤ e ≤ 10
 * 1 ≤ u, v ≤ n
 * 1 ≤ w ≤ 999
 *
 * Sample Test Cases:
 * 
 * Input 1:
 * 3 3
 * 1 2 10
 * 2 3 15
 * 3 1 12
 * 
 * Output 1:
 * Matrix of input data
 * 999	10	999	
 * 999	999	15	
 * 12	999	999	
 * 
 * Transitive closure 
 * 0	10	25	
 * 27	0	15	
 * 12	22	0	
 * 
 * Input 2:
 * 3 3
 * 1 2 4
 * 2 3 6
 * 1 3 10
 * 
 * Output 2:
 * Matrix of input data
 * 999	4	10	
 * 999	999	6	
 * 999	999	999	
 * 
 * Transitive closure 
 * 0	4	10	
 * 999	0	6	
 * 999	999	0
 */



import java.util.Scanner;

class FloydWarshallAlgorithm {

    public static void floyds(int[][] p, int n) {
        int i, j, k;
        for (k = 1; k <= n; k++) {
            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    if (i == j) {
                        p[i][j] = 0;
                    } else {
                        p[i][j] = min(p[i][j], p[i][k] + p[k][j]);
                    }
                }
            }
        }
    }

    public static int min(int a, int b) {
        if (a < b)
            return a;
        else
            return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n, e, u, v, w;
        int[][] p = new int[11][11];  // Using 11 to handle 1-based index
        
        n = scanner.nextInt();
        e = scanner.nextInt();
        
        // Initialize matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                p[i][j] = 999;
            }
        }
        
        // Input edges
        for (int i = 1; i <= e; i++) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextInt();
            p[u][v] = w;
        }
        
        // Print matrix of input data
        System.out.println("Matrix of input data");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(p[i][j] + "\t");
            }
            System.out.println();
        }
        
        floyds(p, n);
        
        // Print transitive closure
        System.out.println("Transitive closure ");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(p[i][j] + "\t");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}


