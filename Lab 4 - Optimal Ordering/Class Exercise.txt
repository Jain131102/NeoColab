/*
Problem Statement

Sophia is managing a data analysis pipeline consisting of multiple computational units represented by matrices. Each unit takes the output from the previous unit as input. Sophia needs to optimize the computational flow by determining the minimum number of scalar multiplications required to process the sequence of matrices.

Additionally, Sophia needs to find the optimal order of matrix multiplications to minimize processing time.

Write a program to help Sophia calculate the minimum cost of matrix multiplications and display the optimal order.

Input format:
The first line contains a single integer n, representing the number of dimensions in the sequence.
The second line contains n integers, representing the dimensions of the matrices.

Output format:
The first line contains an integer representing the minimum number of scalar multiplications required.
The second line contains a string representing the optimal order of matrix multiplications.

Refer to the sample output for formatting specifications.

Code constraints:
The given test cases fall under the following specifications:
3 ≤ n ≤ 100
1 ≤ dimensions of matrices ≤ 500

Sample test cases:
Input 1:
5
1 2 3 4 5

Output 1:
38
(((M1M2)M3)M4)

Input 2:
3
1 2 3

Output 2:
6
(M1M2)
*/



import java.util.*;

 class MatrixChainMultiplication {

    // Recursive function to compute minimum cost of matrix chain multiplication
    public static int minMultRec(int[] arr, int i, int j, int[][] memo, int[][] brackets) {
        if (i + 1 == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int curr = minMultRec(arr, i, k, memo, brackets) +
                    minMultRec(arr, k, j, memo, brackets) +
                    arr[i] * arr[k] * arr[j];

            if (curr < res) {
                res = curr;
                brackets[i][j] = k;
            }
        }

        memo[i][j] = res;
        return res;
    }

    // Function to build the order of matrix multiplications
    public static void getOrder(int i, int j, int[][] brackets, StringBuilder order) {
        if (i + 1 == j) {
            order.append("M").append(i + 1);
            return;
        }

        order.append("(");
        getOrder(i, brackets[i][j], brackets, order);
        getOrder(brackets[i][j], j, brackets, order);
        order.append(")");
    }

    // Main function for matrix chain multiplication
    public static int matrixMultiplication(int[] arr, int n, StringBuilder order) {
        int[][] memo = new int[n][n];
        int[][] brackets = new int[n][n];
        
        // Initialize memo and brackets arrays
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
            Arrays.fill(brackets[i], -1);
        }

        int minCost = minMultRec(arr, 0, n - 1, memo, brackets);
        getOrder(0, n - 1, brackets, order);

        return minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size of matrix chain
        int n = sc.nextInt();

        // Input dimensions of matrices
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // StringBuilder to store the optimal multiplication order
        StringBuilder optimalOrder = new StringBuilder();
        
        // Calculate minimum cost and the order of multiplication
        int result = matrixMultiplication(arr, n, optimalOrder);

        // Output the result
        System.out.println(result);
        System.out.println(optimalOrder.toString());

        sc.close();
    }
}



/*
Problem Statement

Emily is managing a factory that operates on sequential machines represented by matrices. Each machine outputs data in a format that matches the input dimensions of the next machine. To reduce processing time, Emily wants to determine the minimum number of scalar operations required to process the sequence.

Additional Constraint: It should display the optimal order of the Multiplication.

Help Emily to complete the task.

Input format:
The first line contains a single integer n, representing the number of dimensions in the array.
The second line contains n integers, representing the dimensions of the matrices.

Output format:
The output prints the minimum scalar multiplications considering the given constraint.
And then it displays the optimal order of the Multiplication.

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
(((M1M2)M3)M4)

Input 2:
3
10 20 30

Output 2:
6000
(M1M2)
*/


import java.util.*;

 class MatrixChainMultiplication {

    // Recursive function to compute minimum cost of matrix chain multiplication
    public static int minMultRec(int[] arr, int i, int j, int[][] memo, int[][] brackets) {
        if (i + 1 == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int curr = minMultRec(arr, i, k, memo, brackets) +
                    minMultRec(arr, k, j, memo, brackets) +
                    arr[i] * arr[k] * arr[j];

            if (curr < res) {
                res = curr;
                brackets[i][j] = k;
            }
        }

        memo[i][j] = res;
        return res;
    }

    // Function to build the order of matrix multiplications
    public static void getOrder(int i, int j, int[][] brackets, StringBuilder order) {
        if (i + 1 == j) {
            order.append("M").append(i + 1);
            return;
        }

        order.append("(");
        getOrder(i, brackets[i][j], brackets, order);
        getOrder(brackets[i][j], j, brackets, order);
        order.append(")");
    }

    // Main function for matrix chain multiplication
    public static int matrixMultiplication(int[] arr, int n, StringBuilder order) {
        int[][] memo = new int[n][n];
        int[][] brackets = new int[n][n];
        
        // Initialize memo and brackets arrays
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
            Arrays.fill(brackets[i], -1);
        }

        int minCost = minMultRec(arr, 0, n - 1, memo, brackets);
        getOrder(0, n - 1, brackets, order);

        return minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size of matrix chain
        int n = sc.nextInt();

        // Input dimensions of matrices
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // StringBuilder to store the optimal multiplication order
        StringBuilder optimalOrder = new StringBuilder();
        
        // Calculate minimum cost and the order of multiplication
        int result = matrixMultiplication(arr, n, optimalOrder);

        // Output the result
        System.out.println(result);
        System.out.println(optimalOrder.toString());

        sc.close();
    }
}


