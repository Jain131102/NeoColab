/*
Problem Statement

Liam is simulating complex weather patterns using matrices. His simulation requires efficient matrix multiplications to optimize performance. Given a sequence of matrix dimensions, he wants to determine the minimum number of scalar multiplications required to multiply them in the most efficient order.

To further optimize computation:

If n ≥ 3, always prioritize splitting the multiplication at the middle matrix first.
Use dynamic programming and recursion to find the optimal order of multiplication.
Finally, determine and print the minimum cost and the initial optimal split position.

Input format:
The first line contains a single integer n, representing the number of dimensions in the array.
The second line contains n integers, representing the dimensions of the matrices.

Output format:
The output prints an integer representing the minimum number of scalar multiplications required.
The second line prints "Optimal computation starts with a split at matrix X (dimensions: A x B) where X is the index of the splitting matrix, A is its row count, and B is its column count.

Refer to the sample output for formatting specifications.

Code constraints:
The given test cases fall under the following specifications:
3 ≤ n ≤ 100
1 ≤ dimensions of matrices ≤ 500

Sample test cases:
Input 1:
5
1 2 3 4 3

Output 1:
30
Optimal computation starts with a split at matrix 2 (dimensions: 3 x 4).

Input 2:
3
10 20 30

Output 2:
6000
Optimal computation starts with a split at matrix 1 (dimensions: 20 x 30).
*/


import java.util.Scanner;

 class MatrixChainMultiplication {

    // Function to find the middle index for a chain
    public static int findMiddleIndex(int start, int end) {
        return (start + end) / 2;
    }

    // Function to compute the minimum multiplications cost recursively
    public static int minMultiplications(int[] arr, int i, int j, int[][] dp, boolean useMiddle) {
        // Base case: If there's only one matrix, no multiplication is needed
        if (i + 1 == j) {
            return 0;
        }

        // If the result is already computed, return it
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        // Prioritize middle index split if useMiddle flag is set
        if (useMiddle) {
            int mid = findMiddleIndex(i, j);
            if (mid > i && mid < j) { // Ensure mid is a valid split
                int cost = minMultiplications(arr, i, mid, dp, false) +
                           minMultiplications(arr, mid, j, dp, false) +
                           arr[i] * arr[mid] * arr[j];

                if (cost < minCost) {
                    minCost = cost;
                }
            }
        }

        // Try all possible partitions to find the minimum cost
        for (int k = i + 1; k < j; k++) {
            int cost = minMultiplications(arr, i, k, dp, false) +
                       minMultiplications(arr, k, j, dp, false) +
                       arr[i] * arr[k] * arr[j];

            if (cost < minCost) {
                minCost = cost;
            }
        }

        dp[i][j] = minCost; // Store the computed result in dp table
        return minCost;
    }

    // Function to compute the minimum cost of matrix chain multiplication
    public static int matrixChainMultiplication(int[] arr, int n) {
        int[][] dp = new int[100][100];

        // Initialize dp table with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Call the recursive function to compute the minimum cost
        return minMultiplications(arr, 0, n - 1, dp, true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of matrices (n)
        int n = sc.nextInt();

        // Input the dimensions of matrices (this should be n+1 elements)
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Compute the minimum cost of matrix chain multiplication
        int result = matrixChainMultiplication(arr, n);

        // Print the result
        System.out.println(result);

        // Find the middle index for the first split
        int mid = findMiddleIndex(0, n - 1);

        // Print the optimal split information
        if (mid + 1 < n) {
            System.out.printf("Optimal computation starts with a split at matrix %d (dimensions: %d x %d).\n",
                              mid, arr[mid], arr[mid + 1]);
        } else {
            System.out.printf("Optimal computation starts with a split at matrix %d.\n", mid);
        }

        sc.close();
    }
}



/*
Problem Statement

Raj is optimizing the computation of a machine learning model where each layer is represented by a matrix. To reduce computational costs and improve performance, he needs to avoid splitting matrices with large dimensions early in the computation process.

Instead, he aims to defer splitting on the matrix with the largest dimension until all other dimensions have been considered.

Input format:
The first line contains a single integer n, representing the number of dimensions in the array.
The second line contains n integers, p[0], p[1], ..., p[n-1], where p[i] represents the dimensions of the matrices.

Output format:
The output prints a single integer representing the minimum number of scalar multiplications required to compute the product of the matrices in the optimal order.

Code constraints:
The given test cases fall under the following specifications:
3 ≤ n ≤ 100
1 ≤ p[i] ≤ 500

Sample test cases:
Input 1:
5
1 2 3 4 3

Output 1:
51

Input 2:
3
10 20 30

Output 2:
6000
*/


import java.util.Scanner;

 class MatrixChainMultiplication {

    // Function to find the index of the largest dimension in the range (start, end)
    public static int findLargestDimension(int[] arr, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Function to compute the minimum multiplication cost for matrix chain multiplication
    public static int matrixChainMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];

        // Initialize the DP table
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }

        // Compute the minimum cost for different chain lengths
        for (int length = 2; length < n; length++) {
            for (int i = 0; i < n - length; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;

                // Find the largest dimension index in the current range
                int largestDimIndex = findLargestDimension(arr, i + 1, j);

                // Try all possible splits and calculate the cost
                for (int k = i + 1; k < j; k++) {
                    if (k == largestDimIndex && j - i > 2) {
                        continue; // Skip if we encounter the largest dimension for a longer chain
                    }
                    int cost = dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[0][n - 1]; // Return the minimum multiplication cost for the entire chain
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of matrices (n)
        int n = Integer.parseInt(sc.nextLine().trim());  // Read n as a string and parse it

        // Input the dimensions of matrices (this should be n+1 elements)
        String[] input = sc.nextLine().trim().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);  // Parse the array of dimensions
        }

        // Compute the minimum cost of matrix chain multiplication
        int result = matrixChainMultiplication(arr, n);

        // Output the result
        System.out.println(result);

        sc.close();
    }
}


/*
Problem Statement

A computing system needs to process multiple matrix multiplications as part of a data analysis pipeline. Since matrix multiplication is computationally expensive, the system must determine the most efficient order to multiply the matrices to minimize the total number of scalar multiplications.

You are given a sequence of matrices, where each matrix Mi has dimensions pi−1×pi​. Given an array of dimensions dims of size n+1, where the ith matrix Mi has dimensions dims[i-1] × dims[i], determine the optimal way to multiply the matrices with the minimum number of scalar multiplications.

Your task is to compute and print the minimum number of scalar multiplications required for efficient computation.

Input format:
The first line of input consists of a single number n representing the number of matrices.
The second line of input consists of n + 1 integers representing the dimensions of the matrices separated by spaces.

Output format:
The output prints the minimum number of scalar multiplications required.

Code constraints:
The given test cases fall under the following specifications:
1 ≤ n ≤ 100

Sample test cases:
Input 1:
2
10 20 30

Output 1:
Minimum number of multiplications is: 6000

Input 2:
4
10 50 20 30 40

Output 2:
Minimum number of multiplications is: 28000
*/


import java.util.Scanner;

class MatrixChainMultiplication {

    // Function to compute the minimum cost of matrix chain multiplication
    public static int matrixChainOrder(int[] dims, int n) {
        final int MAX = 201; // Maximum number of matrices
        int[][] dp = new int[MAX][MAX];  // DP table to store minimum cost
        int[][] split = new int[MAX][MAX]; // Table to store the split position

        // Initialize the dp table with 0 for single matrices
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // Cost for chains of length 2 to n
        for (int len = 2; len <= n; len++) { // Chain length
            for (int i = 0; i < n - len + 1; i++) { // Starting index of the chain
                int j = i + len - 1; // Ending index of the chain
                dp[i][j] = Integer.MAX_VALUE; // Set the cost to maximum initially

                // Try all possible splits to find the minimum cost
                for (int k = i; k < j; k++) {
                    int q = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    if (q < dp[i][j]) {
                        dp[i][j] = q;
                        split[i][j] = k; // Record the split position
                    }
                }
            }
        }

        // Return the minimum cost for multiplying matrices from 1 to n
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of matrices
        int n = sc.nextInt();

        // Input the dimensions of matrices
        int[] dims = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dims[i] = sc.nextInt();
        }

        // Compute the minimum cost
        int minCost = matrixChainOrder(dims, n);
        System.out.println("Minimum number of multiplications is: " + minCost);

        sc.close();
    }
}



/*
Problem Statement:

Jake is a data scientist working for a large e-commerce company that processes a series of data matrices representing sales data from different regions. Each matrix corresponds to sales data for a specific region and has dimensions that represent the number of products and the number of time periods.

The company needs to combine these matrices to analyze overall sales trends, but the order in which the matrices are combined affects the computational efficiency. Help Jake determine the most efficient way to multiply a chain of matrices to minimize the total computational cost. Compute the minimum number of scalar multiplications needed to multiply the chain of matrices and the optimal parenthesization of the matrices.

Input format:
- The first line of input consists of a single integer n, which represents the number of matrices in the chain.
- The second line of input consists of (n + 1) integers, which represent the dimensions of the matrices in the chain. The first integer indicates the number of rows in the first matrix, and each subsequent integer indicates the number of columns in the corresponding matrix, with the last integer representing the number of columns in the last matrix.

Output format:
- The output prints the Optimal Parenthesization and the Minimum number of multiplications.

Code constraints:
The given test cases fall under the following specifications:
1 ≤ n ≤ 100

Sample test cases:
Input 1:
4
5 10 20 15 30

Output 1:
Optimal Parenthesization: (((A1A2)A3)A4)
Minimum number of multiplications is: 4750

Input 2:
2
10 20 30

Output 2:
Optimal Parenthesization: (A1A2)
Minimum number of multiplications is: 6000
*/


import java.util.Scanner;

 class MatrixChainMultiplication {

    // Function to print the optimal parenthesization
    public static void printOptimalParenthesization(int[][] split, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1)); // Print matrix name (A1, A2, ...)
        } else {
            System.out.print("(");
            printOptimalParenthesization(split, i, split[i][j]);
            printOptimalParenthesization(split, split[i][j] + 1, j);
            System.out.print(")");
        }
    }

    // Function to compute minimum cost of matrix chain multiplication
    public static int matrixChainOrder(int[] dims, int n) {
        int[][] dp = new int[100][100]; // DP table to store minimum cost
        int[][] split = new int[100][100]; // Table to store the split position

        // Cost for chains of length 2 to n
        for (int len = 2; len <= n; ++len) { // Chain length
            for (int i = 0; i < n - len + 1; ++i) { // Starting index of the chain
                int j = i + len - 1; // Ending index of the chain
                dp[i][j] = Integer.MAX_VALUE; // Set the cost to maximum initially

                // Try all possible splits to find the minimum cost
                for (int k = i; k < j; ++k) {
                    int q = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    if (q < dp[i][j]) {
                        dp[i][j] = q;
                        split[i][j] = k; // Record the split position
                    }
                }
            }
        }

        // Print the optimal parenthesization
        System.out.print("Optimal Parenthesization: ");
        printOptimalParenthesization(split, 0, n - 1);
        System.out.println();

        // Return the minimum cost for multiplying matrices from 1 to n
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of matrices
        int n = sc.nextInt();

        // Input the dimensions of matrices
        int[] dims = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            dims[i] = sc.nextInt();
        }

        // Compute the minimum cost
        int minCost = matrixChainOrder(dims, n);
        System.out.println("Minimum number of multiplications is: " + minCost);

        sc.close();
    }
}



