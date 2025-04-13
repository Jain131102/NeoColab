/*
 * Problem Statement:
 * 
 * Alice is tasked with solving the Matrix Chain Multiplication problem using a dynamic programming approach.
 * Given a sequence of matrices and their dimensions, her goal is to determine the minimum number of scalar multiplications
 * required to compute the matrix product.
 *
 * The problem is to find the optimal order of matrix multiplication, as the order in which the matrices are multiplied
 * affects the number of operations.
 *
 * Alice needs help in completing this task.
 *
 * Input format:
 * The first line contains a single integer `n`, representing the number of dimensions in the array.
 *
 * The second line contains `n` integers, p[0], p[1], ..., p[n-1], where p[i] represents the dimensions of the matrices.
 *
 * Output format:
 * The output should print a single integer, the minimum number of scalar multiplications required to compute the product
 * of the matrices in the optimal order.
 *
 * Code constraints:
 * - 3 ≤ n ≤ 100
 * - 1 ≤ p[i] ≤ 500
 *
 * Sample test cases:
 * Input 1:
 * 5
 * 1 2 3 4 3
 * Output 1:
 * 30
 *
 * Input 2:
 * 3
 * 10 20 30
 * Output 2:
 * 6000
 */


import java.util.Scanner;

 class MatrixChainMultiplication {

    // Recursive function with memoization to compute the minimum multiplication cost
    public static int minMultRec(int[] arr, int i, int j, int[][] memo) {
        // Base case: No multiplication needed for a single matrix
        if (i + 1 == j) {
            return 0;
        }

        // Return the result from memo if already computed
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;

        // Try all possible places to split the chain
        for (int k = i + 1; k < j; k++) {
            int curr = minMultRec(arr, i, k, memo) + minMultRec(arr, k, j, memo) + arr[i] * arr[k] * arr[j];

            if (curr < res) {
                res = curr;
            }
        }

        // Store the result in memo
        memo[i][j] = res;
        return res;
    }

    // Main function to calculate the matrix chain multiplication minimum cost
    public static int matrixMultiplication(int[] arr, int n) {
        int[][] memo = new int[n][n];

        // Initialize the memoization table with -1 (indicating not computed)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }

        // Call the recursive function to get the result
        return minMultRec(arr, 0, n - 1, memo);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of matrices (n)
        int n = sc.nextInt();

        // Input the dimensions of matrices
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Call the function to get the result
        int result = matrixMultiplication(arr, n);

        // Output the result
        System.out.println(result);

        sc.close();
    }
}


/*
 * Problem Statement:
 *
 * Liam is simulating complex weather patterns using matrices. His simulation requires accurate and efficient calculations
 * for matrix multiplications. To optimize the computation process, the middle matrix (if any) should always be used as
 * the base for splitting the computation, followed by recursive evaluations.
 *
 * When calculating the optimal order of matrix multiplications, always prioritize splitting using the middle matrix first 
 * if n ≥ 3.
 *
 * Input format:
 * The first line contains a single integer n, representing the number of dimensions in the array.
 *
 * The second line contains n integers, representing the dimensions of the matrices.
 *
 * Output format:
 * The output prints:
 * 1. The minimum number of scalar multiplications required to compute the matrix product.
 * 2. The optimal sequence of matrix splits that minimizes scalar multiplications, starting with the middle matrix split.
 *
 * Code constraints:
 * - 3 ≤ n ≤ 100
 * - 1 ≤ dimensions of matrices ≤ 500
 *
 * Sample test cases:
 * Input 1:
 * 5
 * 1 2 3 4 3
 * Output 1:
 * 51
 * Optimal computation starts with a split at matrix 2 (dimensions: 3 x 4).
 *
 * Input 2:
 * 3
 * 10 20 30
 * Output 2:
 * 6000
 * Optimal computation starts with a split at matrix 1 (dimensions: 20 x 30).
 */



import java.util.Scanner;

 class MatrixChainMultiplication {

    // Function to find the middle index of the chain
    public static int findMiddleIndex(int start, int end) {
        return (start + end) / 2;
    }

    // Recursive function to compute minimum multiplication cost using dynamic programming
    public static int minMultiplications(int[] arr, int i, int j, int[][] dp, boolean useMiddle) {
        if (i + 1 == j) {
            return 0;  // Base case: No multiplication needed for a single matrix
        }

        if (dp[i][j] != -1) {
            return dp[i][j];  // Return already computed value
        }

        int minCost = Integer.MAX_VALUE;

        if (useMiddle) {
            // If we are using the middle index for the first split
            int mid = findMiddleIndex(i, j);
            int cost = minMultiplications(arr, i, mid, dp, false) +
                    minMultiplications(arr, mid, j, dp, false) +
                    arr[i] * arr[mid] * arr[j];
            minCost = Math.min(minCost, cost);
        } else {
            // Try all possible splits for the minimum cost
            for (int k = i + 1; k < j; k++) {
                int cost = minMultiplications(arr, i, k, dp, false) +
                        minMultiplications(arr, k, j, dp, false) +
                        arr[i] * arr[k] * arr[j];
                minCost = Math.min(minCost, cost);
            }
        }

        dp[i][j] = minCost;  // Store the result in dp table
        return minCost;
    }

    // Main function to compute the minimum multiplication cost for the matrix chain
    public static int matrixChainMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];

        // Initialize the dp array with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Call the recursive function starting with using middle index for the first split
        return minMultiplications(arr, 0, n - 1, dp, true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of matrices (n)
        int n = sc.nextInt();

        // Input the dimensions of matrices
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Compute the result using matrix chain multiplication
        int result = matrixChainMultiplication(arr, n);

        // Output the minimum number of multiplications required
        System.out.println(result);

        // Find the middle index for optimal computation split
        int mid = findMiddleIndex(0, n - 1);
        System.out.printf("Optimal computation starts with a split at matrix %d (dimensions: %d x %d).\n",
                mid, arr[mid], arr[mid + 1]);

        sc.close();
    }
}


/*
 * Problem Statement:
 *
 * Ava is training a neural network where each layer is represented by a matrix. To save on energy and computation,
 * the multiplications should avoid splitting matrices with large dimensions at the start.
 *
 * Variation: Avoid splitting on the matrix with the largest dimension until all others are considered.
 *
 * Input format:
 * - The first line contains a single integer n, representing the number of dimensions in the array.
 * - The second line contains n integers, p[0], p[1], ..., p[n-1], where p[i] represents the dimensions of the matrices.
 *
 * Output format:
 * - The output prints a single integer, the minimum number of scalar multiplications required to compute the product
 *   of the matrices in the optimal order.
 *
 * Code constraints:
 * - 3 ≤ n ≤ 100
 * - 1 ≤ p[i] ≤ 500
 *
 * Sample test cases:
 * Input 1:
 * 5
 * 1 2 3 4 3
 * Output 1:
 * 51
 *
 * Input 2:
 * 3
 * 10 20 30
 * Output 2:
 * 6000
 */



import java.util.Scanner;

 class MatrixChainMultiplication {

    // Function to find the matrix with the largest dimension in the range (start, end)
    public static int findLargestDimension(int[] arr, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Function to compute the minimum cost of matrix chain multiplication
    public static int matrixChainMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];

        // Initialize the dp array with 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }

        // Compute the minimum multiplication cost for chains of increasing lengths
        for (int length = 2; length < n; length++) {
            for (int i = 0; i < n - length; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;

                // Find the largest dimension in the current chain
                int largestDimIndex = findLargestDimension(arr, i + 1, j);

                // Try splitting the chain at all possible positions
                for (int k = i + 1; k < j; k++) {
                    // Skip the largest dimension if the chain length is greater than 2
                    if (k == largestDimIndex && j - i > 2) {
                        continue;
                    }

                    int cost = dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        // Return the minimum cost to multiply all matrices
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of matrices (n)
        int n = sc.nextInt();

        // Input the dimensions of matrices
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Call the function to calculate the result
        int result = matrixChainMultiplication(arr, n);

        // Output the result
        System.out.print(result);

        sc.close();
    }
}


