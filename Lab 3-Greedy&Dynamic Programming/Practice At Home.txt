/*
Problem Statement

Reni, an avid traveler, is preparing for her next adventure. She's limited by the amount of weight she can carry in her backpack, but she wants to maximize the value of the items she brings along. Reni knows the value and weight of each item she's considering, and she needs your help to determine which items to pack.

You're tasked with implementing a solution to the 0/1 knapsack problem to assist Reni in selecting the most valuable combination of items without exceeding her backpack's weight limit.

Input format:
The first line of input contains an integer, n, representing the number of items Reni has.
The second line contains n space-separated integers val[i], representing the values of the items.
The third line contains n space-separated integers w[i], representing the weights of the items.
The fourth line contains an integer W, indicating the maximum weight Reni's backpack can hold.

Output format:
The output displays the "Specified weights: " followed by a space-separated list of integers representing the weights of the items that Reni should pack in her backpack to maximize the total value.

Code constraints:
In this scenario, the test cases fall under the following constraints:
1 ≤ N ≤ 10
1 ≤ Val[i] ≤ 100
1 ≤ w[i] ≤ 50
1 ≤ W ≤ 100

Sample test cases:
Input 1:
4
10 20 30 40
1 2 3 4
8
Output 1:
Specified weights: 4 3 1

Input 2:
5
2 3 4 5 6
5 6 8 10 12
7
Output 2:
Specified weights: 6
*/


import java.util.Scanner;

class Knapsack {

    public static int max(int a, int b) { 
        return (a > b) ? a : b; 
    }

    public static void printknapSack(int W, int[] wt, int[] val, int n) {
        int i, w;
        int[][] K = new int[n + 1][W + 1];

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

        w = W;
        System.out.print("Specified weights: ");
        for (i = n; i > 0 && K[i][w] > 0; i--) {
            if (K[i][w] == K[i - 1][w])
                continue;
            else {
                System.out.print(wt[i - 1] + " ");
                w = w - wt[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] val = new int[100];
        int[] wt = new int[100];
        int W, n;

        n = sc.nextInt();

        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        W = sc.nextInt();

        printknapSack(W, wt, val, n);
        
        sc.close();
    }
}

/*
Problem Statement

Sarah loves challenges and has recently taken up a new programming problem. She is fascinated by the classic "0/1 Knapsack Problem" and wants to create a program that can efficiently solve it.

The 0/1 Knapsack Problem involves selecting a combination of items with given weights and values to maximize the total value, while not exceeding a given weight capacity.

Write a program that takes input for the 0/1 Knapsack Problem and outputs the maximum value that can be achieved, as well as the selected weights that contribute to this maximum value.

Input format:
The first line contains an integer n, representing the number of items available.
The second line contains n integers separated by space, representing the values of the items. The ith integer corresponds to the value of the ith item.
The third line contains n integers separated by space, representing the weights of the items. The ith integer corresponds to the weight of the ith item.
The fourth line contains an integer W, representing the maximum weight capacity of the knapsack.

Output format:
The first line output displays "Maximum value: " followed by an integer, representing the maximum value that can be achieved.
The second line output displays "Selected weights: " followed by space-separated integers, representing the selected weights contributing to the maximum value.

Code constraints:
In this scenario, the test cases fall under the following constraints:
1 ≤ n ≤ 10
1 ≤ val[i] ≤ 100
1 ≤ wt[i] ≤ 100
1 ≤ W ≤ 1000

Sample test cases:
Input 1:
3
60 100 120
10 20 30
50
Output 1:
Maximum value: 220
Selected weights: 30 20

Input 2:
4
10 20 30 40
1 2 3 4
8
Output 2:
Maximum value: 80
Selected weights: 4 3 1
*/


import java.util.Scanner;

class Knapsack01 {

    // Function to find the maximum of two integers
    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Function to solve the 0/1 Knapsack problem and print the results
    private static void printknapSack(int W, int[] wt, int[] val, int n) {
        int[][] K = new int[n + 1][W + 1];

        // Build the DP table in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        // Store the result of the knapsack
        int res = K[n][W];
        System.out.println("Maximum value: " + res);

        // Trace the selected weights
        int w = W;
        System.out.print("Selected weights: ");
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != K[i - 1][w]) {  // This item is included.
                System.out.print(wt[i - 1] + " ");
                res = res - val[i - 1]; // Reduce res by the value of this item
                w = w - wt[i - 1];      // Reduce w by the weight of this item
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int[] val = new int[n];
        int[] wt = new int[n];

        // Read values
        for (int i = 0; i < n; i++) {
            val[i] = scanner.nextInt();
        }

        // Read weights
        for (int i = 0; i < n; i++) {
            wt[i] = scanner.nextInt();
        }

        // Read the maximum weight capacity
        int W = scanner.nextInt();

        // Solve the knapsack problem
        printknapSack(W, wt, val, n);
        
        scanner.close();
    }
}

/*
Problem Statement

Liam is packing goods onto his cargo ship, and he wants to maximize the total value of the items he can transport, subject to the ship's weight capacity. However, he can carry only fractions of items, and the value and weight of each item are provided. Sort the items by the value-to-weight ratio in descending order.

Help Liam to determine the maximum value of goods he can pack and list the selected items along with the fractions of each item taken.

Example

Input:
3 30
10 100
15 180
20 240

Output:
2 1.00
3 0.75
360.00

Explanation:

Sort items by value-to-weight ratio:
Item 1: (weight = 10, value = 100) → value-to-weight ratio = 100 / 10 = 10.0
Item 2: (weight = 15, value = 180) → value-to-weight ratio = 180 / 15 = 12.0
Item 3: (weight = 20, value = 240) → value-to-weight ratio = 240 / 20 = 12.0

Sorted by value-to-weight ratio:
Item 2 (12.0)
Item 3 (12.0)
Item 1 (10.0)

Greedy selection:
Item 2 (weight = 15, value = 180): Fully added. Remaining capacity = 30 - 15 = 15.
Item 3 (weight = 20, value = 240): Can only take 15 units (fraction). Remaining capacity = 15.
Fraction taken: 15 / 20 = 0.75.
Value added: 240 * 0.75 = 180.

Total value:
Item 2: 180 (fully taken)
Item 3: 180 (fraction taken)
Total = 180 + 180 = 360.00

Input format:
The first line of the input contains two space-separated values:
N (integer) — the number of goods.
W (double) — the ship's weight capacity.

The next N lines of the input each contain two space-separated values:
weight[i] (double) — the weight of the ith good.
value[i] (double) — the value of the ith good.

Output format:
The first M Lines of output prints i, fraction separated by a space, where i is the 1-based index of the item and fraction is the fraction of the item taken.
The last line of output prints the maximum value of goods, rounded to two decimal places.

Code constraints:
The given test cases fall under the following constraints:
1 ≤ N ≤ 15
1 ≤ value[i], weight[i] ≤ 1000
1 ≤ W ≤ 1000

Sample test cases:
Input 1:
4 50
10 200
20 300
30 150
40 100

Output 1:
1 1.00
2 1.00
3 0.67
600.00

Input 2:
3 30
10 100
15 180
20 240

Output 2:
2 1.00
3 0.75
360.00
*/


import java.util.*;

class Item {
    double weight;
    double value;
    double valuePerWeight;
    int index;

    public Item(int index, double weight, double value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = value / weight;
    }
}

 class FractionalKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();    // Number of goods
        double W = sc.nextDouble();  // Ship's weight capacity
        
        Item[] items = new Item[n];
        
        // Input the items (weight, value) and store them in the items array
        for (int i = 0; i < n; i++) {
            double weight = sc.nextDouble();
            double value = sc.nextDouble();
            items[i] = new Item(i + 1, weight, value);
        }

        // Sort items based on value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        double totalValue = 0.0;
        double remainingWeight = W;

        // Greedy selection of items based on sorted value-to-weight ratio
        for (int i = 0; i < n; i++) {
            if (remainingWeight <= 0) break;

            if (items[i].weight <= remainingWeight) {
                // Fully take the item
                remainingWeight -= items[i].weight;
                totalValue += items[i].value;
                System.out.println(items[i].index + " 1.00");
            } else {
                // Take the fraction of the item
                double fraction = remainingWeight / items[i].weight;
                totalValue += items[i].value * fraction;
                System.out.printf("%d %.2f\n", items[i].index, fraction);
                remainingWeight = 0; // All capacity is used
            }
        }

        // Output the total value of the selected goods, rounded to two decimal places
        System.out.printf("%.2f\n", totalValue);

        sc.close();
    }
}


/*
Problem Statement

You are given a set of items, each with a specific weight and value. Your task is to determine the maximum value that can be obtained by selecting a combination of these items, considering a knapsack with a limited capacity. You are allowed to take fractions of items into the knapsack.

Write a program to implement the fractional knapsack problem.

Input format:
The input consists of several lines, each representing an item.
Each item is represented by two integers separated by a space: the weight of the item followed by its value.
The input terminates with a line containing a single integer -1.
After the items, there is an integer representing the maximum weight capacity.

Output format:
The output prints "The maximum value of the current list is: ", followed by a double value, representing the maximum value that can be obtained by selecting fractions of items to fit into the knapsack, rounded off to two decimal points.

Refer to the sample outputs for the exact format.

Code constraints:
The given test cases fall under the following specifications:
1 ≤ weight and values ≤ 1000
1 ≤ capacity ≤ 100

Sample test cases:
Input 1:
10 60
20 100
30 120
-1
50

Output 1:
The maximum value of the current list is:
240.00

Input 2:
10 100
40 280
20 120
24 120
-1
60

Output 2:
The maximum value of the current list is:
440.00
*/



import java.util.Scanner;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class FractionalKnapsack {

    // Function to swap two items
    public static void swap(Item[] items, int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    // Function to sort items by value-to-weight ratio
    public static void sortByRatio(Item[] items, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double ratio1 = (double) items[j].value / items[j].weight;
                double ratio2 = (double) items[j + 1].value / items[j + 1].weight;
                if (ratio1 < ratio2) {
                    swap(items, j, j + 1);
                }
            }
        }
    }

    // Function to solve the fractional knapsack problem
    public static double fractionalKnapsack(Item[] items, int n, int capacity) {
        double totalValue = 0.0;

        sortByRatio(items, n);

        for (int i = 0; i < n; i++) {
            if (capacity == 0)
                break;

            if (items[i].weight <= capacity) {
                totalValue += items[i].value;
                capacity -= items[i].weight;
            } else {
                totalValue += (double) items[i].value * capacity / items[i].weight;
                capacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item[] items = new Item[1000];
        int n = 0;

        while (true) {
            int weight = scanner.nextInt();
            if (weight == -1)
                break;
            int value = scanner.nextInt();
            items[n] = new Item(weight, value);
            n++;
        }

        int capacity = scanner.nextInt();

        double maxValue = fractionalKnapsack(items, n, capacity);

        System.out.println("The maximum value of the current list is:");
        System.out.printf("%.2f", maxValue);

        scanner.close();
    }
}

