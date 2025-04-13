// Problem Statement
//
// Lorraine is preparing her picnic basket and wants to maximize the value of the items she can carry. 
// Each item has a specific weight and value, and She can pack fractional amounts of items to fill her basket optimally.
//
// Your goal is to calculate the total value She can achieve within the basket's weight capacity.
//
// Example
//
// Input:
// 4 10
// 2 3 5 4
// 40 50 100 60
//
// Output:
// 190.00
//
// Explanation:
//
// Calculate the value-to-weight ratios:
// Item 1: 40 / 2 = 20.0, Item 2: 50 / 3 = 16.67, Item 3: 100 / 5 = 20.0, Item 4: 60 / 4 = 15.0
//
// Sort the items by the value-to-weight ratio (descending):
// Item 1: Ratio = 20.0, Item 3: Ratio = 20.0, Item 2: Ratio = 16.67, Item 4: Ratio = 15.0
//
// Fill the knapsack:
// Select Item 3 (weight = 5, value = 100).
// Select Item 2 (weight = 3, value = 50).
// Take 2/4 of Item 4 (value = 60 × 0.5 = 30).
// Total value: 100 + 50 + 30 = 190.00
//
// Input format:
// The first line of the input consists of two integers A and B separated by space, representing the number of items available and the maximum weight capacity of the basket.
//
// The second line of the input consists of space-separated integers, representing the weights of the items.
//
// The third line of the input consists of space-separated integers, representing the values of the items.
//
// Output format:
// The output prints a double, representing the maximum total value, rounded to two decimal places.
//
// Refer to the sample output for formatting specifications.
//
// Code constraints:
// The given test cases fall under the following specifications:
//
// 1 ≤ A ≤ 10, 1 ≤ B ≤ 10,
// 
// 1 ≤ weight[i] ≤ 103
// 
// 1 ≤ value[i] ≤ 103
//
// Sample test cases:
// Input 1:
// 4 10
// 2 3 5 4
// 40 50 100 60
// Output 1:
// 190.00
//
// Input 2:
// 4 10
// 2 3 4 1
// 10 15 20 5
// Output 2:
// 50.00


import java.util.*;

class FractionalKnapsack {

    static class Item {
        int weight;
        int value;
        double ratio;
        int index;

        Item(int weight, int value, int index) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
            this.index = index;
        }
    }

    // Comparator to sort the items based on their ratio in descending order
    static class ItemComparator implements Comparator<Item> {
        
        public int compare(Item a, Item b) {
            return Double.compare(b.ratio, a.ratio); // descending order
        }
    }

    // Function to calculate the maximum value in the fractional knapsack
    public static double fractionalKnapsack(int N, int W, int[] weights, int[] values) {
        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(weights[i], values[i], i + 1);
        }

        // Sort items by value-to-weight ratio
        Arrays.sort(items, new ItemComparator());

        double totalValue = 0.0;
        int remainingCapacity = W;

        for (int i = 0; i < N; i++) {
            if (remainingCapacity == 0) break;

            if (items[i].weight <= remainingCapacity) {
                remainingCapacity -= items[i].weight;
                totalValue += items[i].value;
            } else {
                totalValue += items[i].value * ((double) remainingCapacity / items[i].weight);
                remainingCapacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of items and the capacity of the knapsack
        int N = sc.nextInt();
        int W = sc.nextInt();
        
        int[] weights = new int[N];
        int[] values = new int[N];

        // Read the weights of the items
        for (int i = 0; i < N; i++) {
            weights[i] = sc.nextInt();
        }

        // Read the values of the items
        for (int i = 0; i < N; i++) {
            values[i] = sc.nextInt();
        }

        // Calculate the maximum value that can be obtained
        double maxValue = fractionalKnapsack(N, W, weights, values);

        // Print the result
        System.out.printf("%.2f\n", maxValue);

        sc.close();
    }
}



// Problem Statement
//
// Alice wants to solve the fractional knapsack problem using a program. She has a fixed list of 4 items, 
// each with a certain value (v) and weight (w). Alice's backpack has a limited weight capacity (W).
//
// The objective is to implement a program that determines the maximum total value of items Alice can carry in her backpack.
//
// Input format:
// The first four lines of input consists of two integers v and w, representing the value and weight of the item separated by space.
//
// The last line consists of an integer W, representing the weight capacity of Alice's backpack.
//
// Output format:
// The first line displays the integers, representing the values of the items, separated by a space.
//
// The second line displays the integer, representing the weights of the items, separated by a space.
//
// The third line shows the float, representing the total weight of items in the backpack, rounded off by two decimal points.
//
// The fourth line displays the float values, representing the maximum value achievable for the given weight capacity, 
// rounded off by two decimal points.
//
// Refer to the sample outputs for the exact format.
//
// Code constraints:
// The given test cases fall under the following specifications:
//
// Fixed the 4 items.
//
// 1 ≤ v, w ≤ 1000
//
// 1 ≤ W ≤ 100
//
// Sample test cases:
//
// Input 1:
// 300 6
// 150 3
// 120 3
// 100 2
// 10
//
// Output 1:
// Values: 300 150 120 100
// Weights: 6 3 3 2
// Total weight in bag: 10.00
// Max value for 10 weight is 500.00
//
// Input 2:
// 100 10
// 280 40
// 120 20
// 120 24
// 60
//
// Output 2:
// Values: 100 280 120 120
// Weights: 10 40 20 24
// Total weight in bag: 60.00
// Max value for 60 weight is 440.00



import java.util.Scanner;

class Item {
    int v;  // Value of the item
    int w;  // Weight of the item
    float d;  // Value-to-weight ratio (v/w)

    Item(int v, int w) {
        this.v = v;
        this.w = w;
        this.d = (float) v / w;  // Calculate value-to-weight ratio
    }
}

 class FractionalKnapsack {

    public static void input(Item[] items) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < items.length; i++) {
            items[i].v = sc.nextInt();
            items[i].w = sc.nextInt();
            items[i].d = (float) items[i].v / items[i].w;  // Calculate ratio for sorting
        }
    }

    public static void display(Item[] items) {
        System.out.print("Values: ");
        for (Item item : items) {
            System.out.print(item.v + " ");
        }
        System.out.println();

        System.out.print("Weights: ");
        for (Item item : items) {
            System.out.print(item.w + " ");
        }
        System.out.println();
    }

    // Sort the items based on value-to-weight ratio in descending order
    public static void bubbleSort(Item[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j].d < items[j + 1].d) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    public static float knapsack(Item[] items, int W) {
        float totalValue = 0, totalWeight = 0;

        // Sort items by value-to-weight ratio in descending order
        bubbleSort(items);

        // Select items based on sorted order
        for (Item item : items) {
            if (totalWeight + item.w <= W) {
                totalValue += item.v;
                totalWeight += item.w;
            } else {
                int remainingWeight = W - (int) totalWeight;
                totalValue += remainingWeight * item.d;  // Add fraction of the item
                totalWeight += remainingWeight;
                break;  // The bag is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Item[] items = new Item[4];

        // Input the item values and weights
        for (int i = 0; i < 4; i++) {
            items[i] = new Item(sc.nextInt(), sc.nextInt());
        }

        // Display item values and weights
        display(items);

        // Input the maximum weight of the backpack
        int W = sc.nextInt();

        // Calculate the maximum value that can be obtained with the given weight
        float maxValue = knapsack(items, W);

        // Calculate the total weight of items in the knapsack
        float totalWeight = 0;
        for (Item item : items) {
            if (totalWeight + item.w <= W) {
                totalWeight += item.w;
            } else {
                int remainingWeight = W - (int) totalWeight;
                totalWeight += remainingWeight;
                break;
            }
        }

        // Output the total weight and maximum value rounded to 2 decimal places
        System.out.printf("Total weight in bag: %.2f\n", totalWeight);
        System.out.printf("Max value for %d weight is %.2f\n", W, maxValue);
    }
}


