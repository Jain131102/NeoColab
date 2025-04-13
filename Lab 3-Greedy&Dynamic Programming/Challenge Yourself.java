// Problem Statement
//
// You are a savvy thief preparing to rob a jewelry store. You have a knapsack with a limited capacity and a list of items, 
// each with a certain weight and a corresponding profit.
//
// Your objective is to maximize the total profit you can obtain by selecting items to place in your knapsack 
// while staying within its weight capacity. Write a program to help you calculate the maximum profit you can achieve.
//
// Note: Use the 0/1 knapsack method to solve the program.
//
// Example:
//
// Input:
// 3
// 60 10
// 100 20
// 120 30
// 50
//
// Output:
// 220
//
// Explanation:
// By selecting the second and third items, you achieve a maximum profit of Rs. 220. 
// The sum of their profits (100 + 120) gives you the highest total profit while 
// not exceeding the knapsack's weight capacity of 50.
//
// Input format:
// The first line contains an integer N, representing the number of items available in the store.
//
// Each of the next N lines contains two space-separated integers P and W, 
// where P is the profit that can be obtained by stealing the item and W is the weight of the item.
//
// The last line contains an integer C representing the weight capacity of your knapsack.
//
// Output format:
// The output displays the maximum total profit that you can achieve by selecting items for your knapsack.
//
// Refer to the sample output for the formatting specifications.
//
// Code constraints:
// The given test cases fall under the formatting specifications:
//
// 1 ≤ N ≤ 10
//
// 1 ≤ P, W ≤ 200
//
// 1 ≤ C ≤ 50
//
// Sample test cases:
//
// Input 1:
// 3
// 60 10
// 100 20
// 120 30
// 50
//
// Output 1:
// 220
//
// Input 2:
// 2
// 10 30
// 20 60
// 70
//
// Output 2:
// 20

import java.util.Scanner;
import java.util.Arrays;

class KnapsackProblem {

    static int[][] dp; // Memoization table

    public static int knapSack(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0)
            return 0;

        if (dp[n][W] != -1) // If already computed, return stored result
            return dp[n][W];

        if (wt[n - 1] > W)
            return dp[n][W] = knapSack(W, wt, val, n - 1);

        return dp[n][W] = Math.max(
                val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1),
                knapSack(W, wt, val, n - 1)
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] profit = new int[n];
        int[] weight = new int[n];

        for (int i = 0; i < n; ++i) {
            profit[i] = scanner.nextInt();
            weight[i] = scanner.nextInt();
        }

        int W = scanner.nextInt();

        dp = new int[n + 1][W + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1); // Initialize memoization table

        System.out.print(knapSack(W, weight, profit, n));
        scanner.close();
    }
}

// Problem Statement
//
// Maria runs a food delivery business with a van that has a limited weight capacity. 
// She needs to select ingredients in such a way that maximizes the total value of the delivery 
// without exceeding the van’s capacity.
//
// Your objective is to help Maria calculate:
// 1. The maximum value of ingredients she can select for the delivery, 
//    considering that she can take fractional amounts of the items (this is a fractional knapsack problem).
// 2. The difference between the maximum value of selected ingredients and the total value of the ingredients.
//
// Example
//
// Input:
// 4 10
// 3 30
// 4 50
// 5 60
// 6 70
//
// Output:
// 121.67
// 88.33
//
// Explanation:
// Item Details: 
// Item 1: (Weight = 3 kg, Value = 30), 
// Item 2: (Weight = 4 kg, Value = 50), 
// Item 3: (Weight = 5 kg, Value = 60), 
// Item 4: (Weight = 6 kg, Value = 70).
//
// Value-to-Weight Ratios:
// Item 2: 50/4 = 12.5, Item 3: 60/5 = 12.0, Item 4: 70/6 = 11.67, Item 1: 30/3 = 10.0.
//
// Sorted by Ratio: 
// Item 2 → Item 3 → Item 4 → Item 1.
//
// Greedy Selection: 
// - Take Item 2 (4 kg, value 50), 
// - Take Item 3 (5 kg, value 60), 
// - Take 1 kg from Item 4, yielding value 11.67.
//
// Total Selected Value: 
// 50 (Item 2) + 60 (Item 3) + 11.67 (part of Item 4) = 121.67.
//
// Total Value of All Items: 
// 30 (Item 1) + 50 (Item 2) + 60 (Item 3) + 70 (Item 4) = 210.
//
// Difference: 
// 210 - 121.67 = 88.33.
//
// Input format:
// The first line contains two values:
//   - An integer N, representing the number of items available,
//   - A double W, representing the weight capacity of the van.
//
// The next N lines contain two space-separated integers: weight[i] and value[i], 
// representing the weight and value of each ingredient (after removing decimal points).
//
// Output format:
// The output consists of two lines:
// 1. The first line prints the double value representing the maximum value, rounded to two decimal places.
// 2. The second line prints the difference between the maximum value and the total value of unselected ingredients, also rounded to two decimal places.
//
// Code constraints:
// The given test cases fall under the following specifications:
// 1 ≤ N ≤ 102
// 1 ≤ weight[i], value[i] ≤ 102
// 1 ≤ W ≤ 102
//
// Sample test cases:
//
// Input 1:
// 4 10
// 3 30
// 4 50
// 5 60
// 6 70
//
// Output 1:
// 121.67
// 88.33
//
// Input 2:
// 3 7
// 3 30
// 4 50
// 5 60
//
// Output 2:
// 86.00
// 54.00



import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight;
    int value;
    double valuePerWeight;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = (double) value / weight;
    }
}

 class FoodDeliveryKnapsack {

    // Comparator for sorting items by value-to-weight ratio in descending order
    public static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            if (item1.valuePerWeight < item2.valuePerWeight) {
                return 1;
            } else if (item1.valuePerWeight > item2.valuePerWeight) {
                return -1;
            }
            return 0;
        }
    }

    public static double greedyKnapsack(int N, double W, Item[] items, double[] totalWeight) {
        double totalValue = 0.0;
        totalWeight[0] = 0.0; // Initialize total weight at index 0

        for (int i = 0; i < N; i++) {
            if (items[i].weight <= W) {
                W -= items[i].weight;
                totalValue += items[i].value;
                totalWeight[0] += items[i].weight;
            } else {
                totalValue += items[i].valuePerWeight * W;
                totalWeight[0] += W;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // Number of ingredients
        double W = sc.nextDouble();  // Weight capacity of the van

        Item[] items = new Item[N];
        double totalValueOfAll = 0.0;

        for (int i = 0; i < N; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();
            items[i] = new Item(weight, value);
            totalValueOfAll += value;
        }

        // Sorting items based on value-to-weight ratio in descending order
        Arrays.sort(items, new ItemComparator());

        double[] totalWeight = new double[1];  // Using array to simulate passing by reference
        double maxValue = greedyKnapsack(N, W, items, totalWeight);

        double unselectedValue = totalValueOfAll - maxValue;

        // Printing the results with two decimal places
        System.out.printf("%.2f\n", maxValue);
        System.out.printf("%.2f\n", unselectedValue);

        sc.close();
    }
}



// Problem Statement

// Helen is planning an adventurous journey and needs to pack her knapsack efficiently to make the most out of her limited carrying capacity. 
// She has a list of valuable items, each with its weight and value.

// However, her knapsack can only hold a certain weight. To maximize the total value of her belongings, 
// she needs your help to choose the items to include in her knapsack.

// Implement the 0/1 Knapsack algorithm to determine the optimal selection of items for her knapsack.

// Input format:
// The first line contains an integer n, representing the total number of items Helen has.
// The next n lines contain two space-separated integers each: val[i] and w[i] denoting the value and weight of the ith item respectively.
// The last line contains an integer W, which represents the maximum weight Helen's knapsack can carry.

// Output format:
// The output prints "Optimal Total Value: " followed by an integer, representing the optimal total value Helen can obtain by packing her knapsack efficiently.

// Code constraints:
// 1 ≤ N ≤ 10
// 1 ≤ val[i] ≤ 200
// 1 ≤ w[i] ≤ 50
// 1 ≤ W ≤ 100

// Sample test cases:

// Input 1:
// 3
// 60 10
// 100 20
// 120 30
// 50

// Output 1:
// Optimal Total Value: 220

// Input 2:
// 4
// 25 5
// 30 8
// 40 10
// 50 15
// 20

// Output 2:
// Optimal Total Value: 75


import java.util.Scanner;
class Knapsack {

    // A utility function that returns maximum of two integers
    static int max(int a, int b) { 
        return (a > b) ? a : b; 
    }

    // Prints the items which are put in a knapsack of capacity W
    static void printKnapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        // stores the result of Knapsack
        int res = K[n][W];
        System.out.printf("Optimal Total Value: %d", res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = scanner.nextInt();
            wt[i] = scanner.nextInt();
        }

        int W = scanner.nextInt();

        printKnapSack(W, wt, val, n);

        scanner.close();
    }
}


/*
Problem Statement

Sam is an adventurous pirate who has recently found a treasure chest on a deserted island. He wants to fill the chest with a selection of treasures, but he can only carry items that fit within the chest's weight limit. Each item has a specific weight and value, and Sam cannot carry fractional items.

Sam's goal is to select a combination of items that maximizes the total value of the treasures while respecting the chest's weight capacity. Can you help Sam choose the best set of treasures to maximize the value of his loot?

Example

Input:

3 10
5 30
5 40
5 50

Output:
90.00

Explanation:
Item 1 and Item 2: Total weight = 10, Total value = 30 + 40 = 70.
Item 1 and Item 3: Total weight = 10, Total value = 30 + 50 = 80.
Item 2 and Item 3: Total weight = 10, Total value = 40 + 50 = 90.
The best combination is Item 2 and Item 3, with a total weight of 10 and a total value of 90.

Input format :
The first line of input consists of two space-separated values an integer N, representing the number of available items, and a double W, representing the maximum weight capacity of the chest.

The next N lines each contain two space-separated values a double weight[i], representing the weight of the i-th item, and a double value[i], representing the value of the i-th item.

Output format :
The output prints the double value representing the maximum value that can be packed into the chest, rounded to two decimal places.

Refer to the sample output for formatting specifications.

Code constraints :
The given test cases fall under the following specifications:

1 ≤ N ≤ 100
0 < W ≤ 103
0 < weight[i], value[i] ≤ 103

Sample test cases :
Input 1 :
3 10
5 30
5 40
5 50
Output 1 :
90.00
Input 2 :
3 20
5 50
10 100
5 60
Output 2 :
210.00
*/


import java.util.*;

class Item {
    double profit, weight;

    Item(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
    }
}

class FractionalKnapsack {
    
    static class ItemComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            double r1 = a.profit / a.weight;
            double r2 = b.profit / b.weight;
            return (r1 > r2) ? -1 : 1;
        }
    }

    public static double fractionalKnapsack(double W, Item[] arr, int N) {
        // Sort items based on the profit/weight ratio
        Arrays.sort(arr, new ItemComparator());

        double finalValue = 0.0;
        for (int i = 0; i < N; i++) {
            if (arr[i].weight <= W) {
                W -= arr[i].weight;
                finalValue += arr[i].profit;
            } else {
                finalValue += arr[i].profit * (W / arr[i].weight);
                break;
            }
        }

        return finalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double W = sc.nextDouble();

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            double weight = sc.nextDouble();
            double profit = sc.nextDouble();
            items[i] = new Item(weight, profit);
        }

        double maxValue = fractionalKnapsack(W, items, N);
        System.out.printf("%.2f\n", maxValue);
    }
}



