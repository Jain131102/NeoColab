import java.util.*;

public class OBST {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of keys
        System.out.print("Enter number of keys: ");
        int n = sc.nextInt();

        // Input: frequencies of each key
        int[] freq = new int[n];
        System.out.println("Enter the frequencies:");
        for (int i = 0; i < n; i++) {
            freq[i] = sc.nextInt();
        }

        // dp[i][j] will store the minimum cost of OBST that can be formed from keys i to j
        int[][] dp = new int[n][n];

        // gap represents the length of the subproblem (i.e., number of keys considered)
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += freq[k]; // total frequency in this range
                }

                int minCost = Integer.MAX_VALUE;

                // Try each key in the range [i..j] as root
                for (int r = i; r <= j; r++) {
                    int leftCost = (r == i) ? 0 : dp[i][r - 1];
                    int rightCost = (r == j) ? 0 : dp[r + 1][j];
                    int totalCost = leftCost + rightCost + sum;

                    if (totalCost < minCost) {
                        minCost = totalCost;
                    }
                }

                dp[i][j] = minCost;
            }
        }

        System.out.println("Minimum cost of OBST: " + dp[0][n - 1]);

        sc.close();
    }
}
