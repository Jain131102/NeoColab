/*
 * Problem Statement:
 *
 * Imagine you're managing a warehouse where different items have varying demand frequencies.
 * Each item is like a key, and its demand frequency is like the frequency of access.
 *
 * Your goal is to organize the items in such a way that accessing them is as efficient as possible, 
 * minimizing the total effort or cost required.
 *
 * For example, items that are frequently accessed together should be placed closer to each other for quicker access. 
 * This is similar to constructing an optimal binary search tree, where the cost of accessing keys is minimized 
 * by organizing them efficiently.
 *
 * The weight matrix W would then represent the total effort or cost of accessing items from one location to another in the warehouse. 
 * This could be used to plan the layout of the warehouse to optimize access and minimize costs.
 *
 * Create a program to display the weight matrix of the OBST.
 *
 * Input format:
 * - The first line of input contains an integer N, representing the number of keys in the tree.
 * - The second line contains N integers, representing the keys themselves.
 * - The third line contains N integers, representing the frequencies of each key.
 * - The fourth line contains N + 1 integers, representing the probabilities of unsuccessful searches for each key and in-between keys.
 *
 * Output format:
 * - The program should output the weight matrix W where each row represents a starting key and each column represents an ending key.
 *
 * Code constraints:
 * - 1 ≤ N ≤ 10
 * - 1 ≤ Keys, Frequencies, Probabilities ≤ 100
 *
 * Sample test cases:
 * Input 1:
 * 2
 * 10 20
 * 1 1
 * 1 2 1
 *
 * Output 1:
 * 1 4 6
 * 2 4
 * 1
 *
 * Input 2:
 * 4
 * 10 20 30 40
 * 4 2 6 3
 * 2 3 1 5 6
 *
 * Output 2:
 * 2 9 12 23 32
 * 3 6 17 26
 * 1 12 21
 * 5 14
 * 6
 */


 import java.util.Scanner;

interface OBSTOperations {
    void computeW();
}

class OBST implements OBSTOperations {
    private static final int NMAX = 20;
    private int[][] W = new int[NMAX][NMAX];
    private int[] q = new int[NMAX]; // Unsuccessful searches
    private int[] p = new int[NMAX]; // Frequencies
    private int[] keys = new int[NMAX];
    private int numberOfKeys;

    public OBST(int numberOfKeys, int[] keys, int[] p, int[] q) {
        this.numberOfKeys = numberOfKeys;
        System.arraycopy(keys, 0, this.keys, 0, numberOfKeys + 1);
        System.arraycopy(p, 0, this.p, 0, numberOfKeys + 1);
        System.arraycopy(q, 0, this.q, 0, numberOfKeys + 1);
    }

    @Override
    public void computeW() {
        for (int i = 0; i <= numberOfKeys; i++) {
            W[i][i] = q[i];
            for (int j = i + 1; j <= numberOfKeys; j++) {
                W[i][j] = W[i][j - 1] + p[j] + q[j];
            }
        }

        for (int i = 0; i <= numberOfKeys; i++) {
            for (int j = i; j <= numberOfKeys; j++) {
                System.out.print(W[i][j] + " ");
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
        obst.computeW();
    }
}



/*
 * Problem Statement:
 * 
 * Mansi, a dedicated and hardworking developer, is working on a project involving Optimal Binary Search Trees (OBSTs).
 * These specialized data structures help minimize the average search cost for a given set of keys and their associated frequencies.
 * 
 * Her task is to design a program that computes the cost of an Optimal Binary Search Tree based on a given set of keys and their frequencies.
 * Assist her in building an efficient solution for this problem.
 * 
 * Input format:
 * - The first line of input consists of an integer n, representing the number of keys in the existing OBST.
 * - The second line of input consists of n space-separated integers, each representing a unique key K.
 * - The third line of input consists of n space-separated integers, each representing the frequency F of the corresponding key.
 * 
 * Output format:
 * - The output should consist of a single-line cost of the Optimal Binary Search Tree for the given set of keys and frequencies.
 * 
 * Code constraints:
 * - 1 ≤ n ≤ 10
 * - 1 ≤ K, F ≤ 100
 * 
 * Sample test cases:
 * Input 1:
 * 3
 * 10 12 20
 * 34 8 50
 * Output 1:
 * Cost of Optimal BST is 142
 * 
 * Explanation:
 * In this problem, we need to calculate the cost of constructing an Optimal Binary Search Tree (OBST) using dynamic programming. 
 * We will compute the minimum cost to search for a key in the tree while taking into account the frequencies of the keys.
 */



import java.util.*;

interface OptimalBST {
    int findOptimalCost(int[] keys, int[] frequency, int n);
}

 class OptimalBSTJava {
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
        
        OptimalBST bst = new OptimalBST() {
            public int findOptimalCost(int[] keys, int[] frequency, int n) {
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
            
            private int sum(int[] frequency, int i, int j) {
                int s = 0;
                for (int k = i; k <= j; k++) {
                    s += frequency[k];
                }
                return s;
            }
        };
        
        System.out.println("Cost of Optimal BST is " + bst.findOptimalCost(keys, frequency, n));
    }
}


/*
 * Problem Statement:
 * 
 * Rohan, a passionate programmer, is working on optimizing search operations in databases.
 * His mentor has introduced him to Optimal Binary Search Trees (OBSTs)—a special type of binary search tree 
 * designed to minimize the average search time for a set of keys with given frequencies.
 * 
 * As part of his project, Rohan needs to develop a program that determines whether a given key exists in an OBST 
 * using dynamic programming. Help him implement an efficient solution to achieve this task.
 * 
 * Input format:
 * - The first line consists of an integer n, representing the number of keys and frequencies in the OBST.
 * - The next n lines contain two integers, key[i] and freq[i]. These represent the keys and their corresponding frequencies, separated by a space.
 * - The last line consists of the integer m, representing the key Rohan wants to search for in the OBST.
 * 
 * Output format:
 * - If the key is found in the OBST, print "Key <m> found in the OBST."
 * - If the key is not found in the OBST, print "Key <m> not found in the OBST."
 * 
 * Code constraints:
 * - 1 ≤ n ≤ 10
 * - 1 ≤ key[i] ≤ 100
 * - 1 ≤ freq[i] ≤ 100
 * - 1 ≤ m ≤ 100
 * 
 * Sample test cases:
 * Input 1:
 * 3
 * 1 3
 * 2 5
 * 3 7
 * 2
 * Output 1:
 * Key 2 found in the OBST.
 * 
 * Input 2:
 * 4
 * 3 9
 * 4 12
 * 5 8
 * 1 2
 * 2
 * Output 2:
 * Key 2 not found in the OBST.
 * 
 * Explanation:
 * In this problem, we are tasked with searching for a key in the OBST. We are given the keys and their frequencies, 
 * and we need to check if a particular key exists in the OBST or not.
 * The result is printed based on whether the key is present in the tree or not.
 */



import java.util.Scanner;

interface OBSTOperations {
    boolean searchOBST(int[] keys, int[] freq, int n, int key);
}

class OBST implements OBSTOperations {
    @Override
    public boolean searchOBST(int[] keys, int[] freq, int n, int key) {
        int[][] cost = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
        }

        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                cost[i][j] = Integer.MAX_VALUE;

                for (int r = i; r <= j; r++) {
                    int c = ((r > i) ? cost[i][r - 1] : 0) +
                            ((r < j) ? cost[r + 1][j] : 0) +
                            (r < n ? freq[r] : 0);

                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                    }
                }
            }
        }

        int i = 0, j = n - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (keys[mid] == key) {
                return true;
            } else if (keys[mid] < key) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] keys = new int[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();
            freq[i] = sc.nextInt();
        }

        int keyToFind = sc.nextInt();
        OBST obst = new OBST();
        boolean result = obst.searchOBST(keys, freq, n, keyToFind);

        if (result) {
            System.out.println("Key " + keyToFind + " found in the OBST.");
        } else {
            System.out.println("Key " + keyToFind + " not found in the OBST.");
        }
    }
}



