/*
 * Problem Statement:
 *
 * The task is to construct an Optimal Binary Search Tree (OBST) and calculate the cost matrix for the OBST, given a set of 
 * keys, their frequencies, and the probabilities of unsuccessful searches.
 * 
 * The goal is to calculate the cost matrix C, where each entry represents the cost of a subtree in the OBST. The cost of 
 * a subtree is defined as the total frequency of all the keys in that subtree multiplied by the distance from the root of 
 * that subtree to each key.
 * 
 * Input format:
 * 
 * The first line of input consists of an integer N, the number of keys in the tree.
 * 
 * The second line consists of N space-separated integers, representing the keys.
 * 
 * The third line consists of N space-separated integers, representing the frequencies of the corresponding keys.
 * 
 * The fourth line consists of N+1 space-separated integers, representing the probabilities of unsuccessful searches.
 * 
 * Output format:
 * 
 * The output displays the cost matrix C, where C[i][j] represents the cost of the subtree rooted between keys[i] and keys[j].
 * 
 * Each row of the matrix should be printed on a new line, with elements separated by spaces.
 * 
 * Code constraints:
 * 
 * 1 ≤ N ≤ 10
 * 1 ≤ Keys, Frequencies, and Probabilities ≤ 1000
 * 
 * Sample Test Case 1:
 * 
 * Input:
 * 3
 * 10 20 30
 * 1 1 2
 * 1 2 1 2
 * 
 * Output:
 * 1 7 14 25 
 * 2 7 17 
 * 1 8 
 * 2
 * 
 * Sample Test Case 2:
 * 
 * Input:
 * 3
 * 10 20 30
 * 3 1 6
 * 1 2 3 4
 * 
 * Output:
 * 1 9 22 46 
 * 2 11 31 
 * 3 20 
 * 4
 * 
 * Explanation:
 * The program needs to compute the OBST cost matrix based on the given keys, frequencies, and probabilities of unsuccessful 
 * searches. Each entry in the cost matrix represents the cost of a subtree in the optimal binary search tree.
 */


import java.util.Scanner;

interface OBSTOperations {
    void computeWCR();
}

class OBST implements OBSTOperations {
    private static final int NMAX = 20;
    private int[][] C = new int[NMAX][NMAX];
    private int[][] W = new int[NMAX][NMAX];
    private int[][] R = new int[NMAX][NMAX];
    private int[] q = new int[NMAX];
    private int[] p = new int[NMAX];
    private int[] keys = new int[NMAX];
    private int numberOfKeys;

    public OBST(int numberOfKeys, int[] keys, int[] p, int[] q) {
        this.numberOfKeys = numberOfKeys;
        System.arraycopy(keys, 0, this.keys, 0, numberOfKeys + 1);
        System.arraycopy(p, 0, this.p, 0, numberOfKeys + 1);
        System.arraycopy(q, 0, this.q, 0, numberOfKeys + 1);
    }

    @Override
    public void computeWCR() {
        for (int i = 0; i <= numberOfKeys; i++) {
            W[i][i] = q[i];
            for (int j = i + 1; j <= numberOfKeys; j++) {
                W[i][j] = W[i][j - 1] + p[j] + q[j];
            }
        }
        
        for (int i = 0; i <= numberOfKeys; i++) {
            C[i][i] = W[i][i];
        }
        
        for (int i = 0; i < numberOfKeys; i++) {
            int j = i + 1;
            C[i][j] = C[i][i] + C[j][j] + W[i][j];
            R[i][j] = j;
        }
        
        for (int h = 2; h <= numberOfKeys; h++) {
            for (int i = 0; i <= numberOfKeys - h; i++) {
                int j = i + h;
                int m = R[i][j - 1];
                int min = C[i][m - 1] + C[m][j];
                for (int k = m + 1; k <= R[i + 1][j]; k++) {
                    int x = C[i][k - 1] + C[k][j];
                    if (x < min) {
                        m = k;
                        min = x;
                    }
                }
                C[i][j] = W[i][j] + min;
                R[i][j] = m;
            }
        }
        
        for (int i = 0; i <= numberOfKeys; i++) {
            for (int j = i; j <= numberOfKeys; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfKeys = sc.nextInt();
        int[] keys = new int[numberOfKeys + 1];
        int[] p = new int[numberOfKeys + 1];
        int[] q = new int[numberOfKeys + 1];

        for (int i = 1; i <= numberOfKeys; i++) {
            keys[i] = sc.nextInt();
        }
        for (int i = 1; i <= numberOfKeys; i++) {
            p[i] = sc.nextInt();
        }
        for (int i = 0; i <= numberOfKeys; i++) {
            q[i] = sc.nextInt();
        }

        OBST obst = new OBST(numberOfKeys, keys, p, q);
        obst.computeWCR();
    }
}


/*
 * Problem Statement:
 *
 * Imagine you have a set of items, each with its popularity or frequency of use. 
 * You want to organize these items to minimize the average search time. 
 * This is similar to organizing a library where books are frequently borrowed, 
 * so you want to arrange them to make finding books quick and efficient.
 *
 * Your task is to write a program that takes the probabilities of each item as input 
 * and constructs an optimal binary search tree (OBST). The program should calculate 
 * the minimum cost of constructing the OBST, which represents the average search time, 
 * and determine the root of the constructed tree, which indicates the optimal starting 
 * point for searching items.
 *
 * Create a program to find the minimum cost and root of OBST.
 *
 * Input format:
 * The first line consists of an integer n, representing the number of elements.
 *
 * The second line contains n space-separated integers representing the elements of the keys.
 *
 * The third line contains n+1 space-separated integers representing the probabilities.
 *
 * Output format:
 * The first line of output displays the Minimum cost of the OBST.
 *
 * The second line of output displays the root of the given OBST.
 *
 * Code constraints:
 * 1 ≤ n ≤ 10
 * 1 ≤ element, probability ≤ 103
 *
 * Sample test cases:
 *
 * Input 1:
 * 4
 * 3 3 1 1 
 * 2 3 1 1 1
 *
 * Output 1:
 * Minimum cost = 32
 * Root = 2
 *
 * Input 2:
 * 2
 * 10 20
 * 1 2 2
 *
 * Output 2:
 * Minimum cost = 48
 * Root = 2
 */


import java.util.Scanner;

interface OBSTOperations {
    void computeOptimalBST();
}

class OBST implements OBSTOperations {
    private static final int MAX = 10;
    private int[][] W = new int[MAX][MAX];
    private int[][] C = new int[MAX][MAX];
    private int[][] R = new int[MAX][MAX];
    private int[] p = new int[MAX];
    private int[] q = new int[MAX];
    private int numberOfKeys;

    public OBST(int numberOfKeys, int[] p, int[] q) {
        this.numberOfKeys = numberOfKeys;
        System.arraycopy(p, 0, this.p, 0, numberOfKeys + 1);
        System.arraycopy(q, 0, this.q, 0, numberOfKeys + 1);
    }

    @Override
    public void computeOptimalBST() {
        for (int i = 0; i <= numberOfKeys; i++) {
            for (int j = 0; j <= numberOfKeys; j++) {
                if (i == j) {
                    W[i][j] = q[i];
                    C[i][j] = 0;
                    R[i][j] = 0;
                }
            }
        }

        for (int b = 0; b < numberOfKeys; b++) {
            for (int i = 0, j = b + 1; j <= numberOfKeys; j++, i++) {
                if (i != j && i < j) {
                    W[i][j] = p[j] + q[j] + W[i][j - 1];
                    int min = Integer.MAX_VALUE;
                    int temp = 0;
                    for (int k = i + 1; k <= j; k++) {
                        int min1 = C[i][k - 1] + C[k][j] + W[i][j];
                        if (min > min1) {
                            min = min1;
                            temp = k;
                        }
                    }
                    C[i][j] = min;
                    R[i][j] = temp;
                }
            }
        }
        System.out.println("Minimum cost = " + C[0][numberOfKeys]);
        System.out.print("Root = " + R[0][numberOfKeys]);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfKeys = sc.nextInt();
        int[] p = new int[numberOfKeys + 1];
        int[] q = new int[numberOfKeys + 1];

        for (int i = 1; i <= numberOfKeys; i++) {
            p[i] = sc.nextInt();
        }
        for (int i = 0; i <= numberOfKeys; i++) {
            q[i] = sc.nextInt();
        }

        OBST obst = new OBST(numberOfKeys, p, q);
        obst.computeOptimalBST();
    }
}


/*
 * Problem Statement:
 *
 * Computer science students are exploring the concept of Optimal Binary Search Trees (OBSTs) and have been assigned a problem related to it.
 *
 * Your task is to develop a program that allows students to efficiently remove a key from an OBST and compute the updated cost of the OBST after deletion.
 *
 * An OBST is a binary search tree designed to minimize the average search time based on given key frequencies.
 *
 * Given a set of keys and their respective frequencies, implement a program that deletes a specified key from the OBST and determines the resulting cost of the tree.
 *
 * Input format:
 * - The first line contains an integer n, representing the number of keys in the OBST.
 * - The second line contains n space-separated integers, representing the keys in the OBST.
 * - The third line contains n space-separated integers, representing the frequencies of the corresponding keys.
 * - The fourth line contains an integer r, representing the key to be deleted from the OBST.
 *
 * Output format:
 * - The output displays one of the following:
 *   1. If the specified key is found in the OBST, output a single line containing the cost of the OBST after deleting the key.
 *   2. Otherwise, the output is "Key not found in the OBST."
 *
 * Code constraints:
 * - 1 <= n <= 10
 * - 1 <= keys <= 100
 * - 1 <= frequency <= 100
 * - 1 <= r <= 100
 *
 * Sample test cases:
 * Input 1:
 * 4
 * 10 12 20 9
 * 34 8 50 18
 * 9
 *
 * Output 1:
 * Cost of Optimal BST after deleting the key is 142
 *
 * Input 2:
 * 5
 * 10 20 30 40 50
 * 4 2 6 3 1
 * 60
 *
 * Output 2:
 * Key not found in the OBST.
 */




import java.util.Scanner;

interface OBSTOperations {
    int computeOBST(int[] keys, int[] frequency, int n);
    int deleteKey(int[] keys, int[] frequency, int n, int keyToDelete);
}

class OBST implements OBSTOperations {
    private int sum(int[] frequency, int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++) {
            s += frequency[k];
        }
        return s;
    }

    public int computeOBST(int[] keys, int[] frequency, int n) {
        int[][] cost = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            cost[i][i] = frequency[i];
        }

        for (int chainLen = 2; chainLen <= n; chainLen++) {
            for (int i = 0; i <= n - chainLen; i++) {
                int j = i + chainLen - 1;
                cost[i][j] = Integer.MAX_VALUE;

                for (int r = i; r <= j; r++) {
                    int leftCost = (r > i) ? cost[i][r - 1] : 0;
                    int rightCost = (r < j) ? cost[r + 1][j] : 0;
                    int currentCost = leftCost + rightCost + sum(frequency, i, j);

                    if (currentCost < cost[i][j]) {
                        cost[i][j] = currentCost;
                    }
                }
            }
        }
        return cost[0][n - 1];
    }

    public int deleteKey(int[] keys, int[] frequency, int n, int keyToDelete) {
        int keyIndex = -1;
        for (int i = 0; i < n; i++) {
            if (keys[i] == keyToDelete) {
                keyIndex = i;
                break;
            }
        }

        if (keyIndex == -1) {
            return Integer.MAX_VALUE;
        }

        for (int i = keyIndex; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            frequency[i] = frequency[i + 1];
        }

        return computeOBST(keys, frequency, n - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] keys = new int[n];
        int[] frequency = new int[n];

        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            frequency[i] = sc.nextInt();
        }

        int keyToDelete = sc.nextInt();
        OBST obst = new OBST();
        int deletedCost = obst.deleteKey(keys, frequency, n, keyToDelete);

        if (deletedCost == Integer.MAX_VALUE) {
            System.out.println("Key not found in the OBST.");
        } else {
            System.out.println("Cost of Optimal BST after deleting the key is " + deletedCost);
        }
    }
}


