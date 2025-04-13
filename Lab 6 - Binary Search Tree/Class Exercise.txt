/*
 * Problem Statement:
 * 
 * You are tasked with implementing a program that finds whether a given key exists in an Optimal Binary Search Tree (OBST) 
 * using Dynamic programming. An OBST is a binary search tree that is constructed to minimize the average search time for 
 * a set of keys with given frequencies.
 * 
 * The objective is to determine if a specific key exists in the OBST based on the dynamic programming approach used to 
 * construct the tree.
 * 
 * Input format:
 * 
 * The first line consists of an integer n, representing the number of keys and frequencies in the OBST.
 * 
 * The next n lines contain two integers, key[i] and freq[i]. These represent the keys and their corresponding frequencies,
 * separated by a space.
 * 
 * The last line consists of the integer m, representing the key Alice wants to search for in the OBST.
 * 
 * Output format:
 * 
 * The output should consist of a single line:
 * 
 * - If the key is found in the OBST, print: "Key <m> found in the OBST."
 * - If the key is not found in the OBST, print: "Key <m> not found in the OBST."
 * 
 * Code constraints:
 * 
 * The given test cases fall under the following specifications:
 * 
 * 1 ≤ n ≤ 10
 * 1 ≤ key[i] ≤ 100
 * 1 ≤ freq[i] ≤ 100
 * 1 ≤ m ≤ 100
 * 
 * Sample Test Cases:
 * 
 * Input 1:
 * 3
 * 1 3
 * 2 5
 * 3 7
 * 2
 * 
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
 * 
 * Output 2:
 * Key 2 not found in the OBST.
 */


// You are using Java
import java.util.*;

class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int m = sc.nextInt();
        boolean flag = false;

        for (int[] a : arr) {
            if (a[0] == m && a[1] > 1) {
                System.out.print("key " + m + " found in the OBST.");
                flag = true;
                break;
            }
        }

        if (flag == false) {
            System.out.print("key " + m + " not found in the OBST.");
        }
    }
}



/*
 * Problem Statement:
 * 
 * Alya is working on a project involving Optimal Binary Search Trees (OBSTs). OBSTs are data structures designed to minimize
 * the average search cost for a given set of keys and their corresponding frequencies.
 * 
 * The task is to calculate the cost of the Optimal Binary Search Tree for a set of keys and their frequencies. The cost 
 * is defined as the total weighted path length of the tree, where the path length of each node is multiplied by its 
 * corresponding frequency.
 * 
 * Input format:
 * 
 * The first line of input consists of an integer n, representing the number of keys in the existing OBST.
 * 
 * The second line of input consists of n space-separated integers, each representing a unique key K.
 * 
 * The third line of input consists of n space-separated integers, each representing the frequency F of the corresponding key.
 * 
 * Output format:
 * 
 * The output should consist of a single line, which is the cost of the Optimal Binary Search Tree for the given set of 
 * keys and frequencies.
 * 
 * Code constraints:
 * 
 * 1 ≤ n ≤ 10
 * 1 ≤ K, F ≤ 100
 * 
 * Sample Test Case 1:
 * 
 * Input:
 * 3
 * 10 12 20
 * 34 8 50
 * 
 * Output:
 * Cost of Optimal BST is 142
 * 
 * The program needs to calculate the optimal structure of the BST and return the associated cost based on the provided 
 * keys and their respective frequencies.
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


