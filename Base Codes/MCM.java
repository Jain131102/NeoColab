
import java.util.Scanner;

public class MCM { // Lab 4

    static int dp[][];
    static int bracket[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dims[] = new int[n];

        for (int i = 0; i < n; i++) {
            dims[i] = sc.nextInt();
        }
        dp = new int[n][n];
        bracket = new int[n][n];

        for (int i = n - 1; i >=1; i--) {
            for (int j = i + 1; j < n; j++) {
                int mini = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int steps = dp[i][k] + dp[k + 1][j]
                            + dims[i - 1] * dims[k] * dims[j];

                    if (steps < mini) {
                        mini = steps;
                        bracket[i][j] = k;
                    }
                }
                dp[i][j] = mini;
            }
        }
        System.out.println(dp[1][n - 1]);
        String name[] = new String[n - 1];
        for (int i = 0; i < name.length; i++) {
            name[i] = "M" + (i + 1);
        }
        StringBuilder result=new StringBuilder();
        helper(1, n - 1, name,result);
        System.out.println(result);
    }

    public static void helper(int i, int j, String name[],StringBuilder result) {
        if (i == j) {
            result.append(name[i - 1]);
            return;
        }
        result.append("(");
        helper(i, bracket[i][j], name,result);
        result.append("x");
        helper(bracket[i][j] + 1, j, name,result);
        result.append(")");
    }
}
