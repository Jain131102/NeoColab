/*
 * Problem Statement:
 * 
 * Captain Aidan, a daring pirate, has discovered an ancient treasure cave filled with valuable relics.
 * However, the cave is protected by an ancient spell that limits the weight of the treasure he can carry.
 * Aidan has a magic knapsack with a maximum weight capacity of W. Inside the cave, he finds n different treasures, 
 * each with a specific weight and value.
 * 
 * Since he can only take each treasure once, Aidan must carefully choose which items to take to maximize 
 * the total value without exceeding the weight limit of his knapsack.
 * 
 * Help Captain Aidan pick the most valuable treasures while staying within the weight limit using branch and bound technique.
 * 
 * Input format:
 * - The first line contains two integers n (the number of items) and W (the capacity of the knapsack), separated by a space.
 * - The second line contains n integers, where the i-th integer represents the weight of the i-th item.
 * - The third line contains n integers, where the i-th integer represents the value of the i-th item.
 * 
 * Output format:
 * - The output prints the single integer representing the maximum value obtained by filling the knapsack with the given set of items.
 * 
 * Code constraints:
 * - 1 ≤ n ≤ 100 (Number of items)
 * - 1 ≤ W ≤ 1000 (Capacity of the knapsack)
 * - 1 ≤ weight of item ≤ 1000 (Weight of each item)
 * - 1 ≤ value of item ≤ 1000 (Value of each item)
 * 
 * Sample test cases:
 * Input 1:
 * 5 60
 * 10 20 30 40 50
 * 60 100 120 140 180
 * Output 1:
 * 280
 * 
 * Input 2:
 * 4 30
 * 2 8 12 20
 * 40 60 80 100
 * Output 2:
 * 200
 * 
 * Explanation:
 * The goal is to maximize the value in the knapsack while ensuring that the weight of the selected items 
 * doesn't exceed the given weight capacity W.
 * We will use the branch and bound technique to explore potential item selections and prune the search tree 
 * when it is guaranteed that no better solution can be found along that path.
 */



// You are using Java
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int wt[] = new int[n];
        int val[] = new int[n];

        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        //int W = sc.nextInt();

        System.out.println(helper(0, n, W, wt, val));
    }

    public static int helper(int i, int n, int W, int wt[], int val[]) {
        if (i >= n)
            return 0;

        int include = 0;
        if (wt[i] <= W)
            include += helper(i + 1, n, W - wt[i], wt, val) + val[i];
        int exclude = helper(i + 1, n, W, wt, val);

        return Math.max(include, exclude);
    }
}


/*
 * Problem Statement:
 * 
 * Raju is a professional hiker preparing for a long trekking expedition. He has a backpack that can hold a maximum weight of W kilograms.
 * There are N essential items you want to carry, each with a specific weight and importance value (utility score).
 * His goal is to maximize the total importance value of the items you take while ensuring that the total weight does not exceed the backpack's capacity.
 * 
 * He can either include or exclude each item—meaning he cannot take fractions of an item (e.g., you cannot take half of a tent).
 * Given the weights and importance values of the items, determine the maximum possible importance value he can carry in his backpack.
 * Help him to achieve his task using the backtracking technique.
 * 
 * Input format:
 * - The first line contains two integers, N (number of items) and W (maximum weight the backpack can carry).
 * - The second line contains N integers, representing the weights of the items.
 * - The third line contains N integers, representing the importance values of the items.
 * 
 * Output format:
 * - The output prints a single integer, which is the maximum importance value that can be achieved without exceeding the weight limit.
 * 
 * Code constraints:
 * - 1 ≤ N ≤ 20
 * - 1 ≤ W ≤ 105
 * - 1 ≤ weight of each item ≤ W
 * - 1 ≤ value of each item ≤ 104
 * 
 * Sample test cases:
 * Input 1:
 * 4 5
 * 2 3 4 5
 * 3 4 5 6
 * Output 1:
 * 7
 * 
 * Input 2:
 * 5 10
 * 2 3 4 5 6
 * 3 4 8 8 10
 * Output 2:
 * 18
 * 
 * Explanation:
 * The goal is to maximize the importance value in the backpack while ensuring that the weight of the selected items 
 * doesn't exceed the given weight capacity W.
 * We will use backtracking to explore different combinations of items to take, trying both the possibility of including 
 * and excluding each item, and recursively checking which combination gives the maximum importance value without exceeding the weight limit.
 */



// You are using Java
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W=sc.nextInt();
        int wt[] = new int[n];
        int val[] = new int[n];

        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        //int W = sc.nextInt();

        System.out.println(helper(0, n, W, wt, val));
    }

    public static int helper(int i, int n, int W, int wt[], int val[]) {
        if (i >= n)
            return 0;

        int include = 0;
        if (wt[i] <= W)
            include += helper(i + 1, n, W - wt[i], wt, val) + val[i];
        int exclude = helper(i + 1, n, W, wt, val);

        return Math.max(include, exclude);
    }
}
