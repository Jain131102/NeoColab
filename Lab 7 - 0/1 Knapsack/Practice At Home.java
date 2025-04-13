/*
 * Problem Statement:
 *
 * A warehouse manager needs to ship valuable items with a weight limit on a transport vehicle. 
 * Each item has:
 * - A weight (in kg).
 * - A value (profit earned by shipping the item).
 *
 * The goal is to maximize the total profit while ensuring that the total weight does not exceed the vehicle’s maximum capacity.
 *
 * The manager decides to use a branch-and-bound approach to select items optimally.
 *
 * Input format:
 * - The first line contains a single integer N, representing the number of items available in the warehouse.
 * - The next N lines each contain two integers: weight[i] and value[i], where weight[i] is the weight of the item and value[i] is the value (profit) of the item.
 * - The next line contains a single integer W, representing the vehicle's maximum weight capacity.
 *
 * Output format:
 * - The output prints a single integer representing the maximum profit that can be achieved within the weight limit.
 *
 * Code constraints:
 * 1 ≤ N ≤ 1000
 * 1 ≤ weight[i] ≤ 1000
 * 1 ≤ value[i] ≤ 1000
 * 1 ≤ W ≤ 1000
 *
 * Sample test cases:
 *
 * Input 1:
 * 4
 * 4 70
 * 2 30
 * 1 20
 * 3 40
 * 6
 * 
 * Output 1:
 * 100
 *
 * Input 2:
 * 3
 * 10 200
 * 15 250
 * 20 300
 * 10
 * 
 * Output 2:
 * 200
 *
 * Explanation:
 * - The manager needs to choose the best combination of items that maximizes total profit without exceeding the weight limit.
 * - The branch-and-bound approach helps prune unnecessary paths and efficiently finds the optimal selection of items.
 */


import java.util.*;

class Item {
    double weight;
    int value;
    double ratio;

    public Item(double weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = value / weight;
    }
}

class Node {
    int level, profit, bound;
    double weight;

    public Node(int level, int profit, double weight, int bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }
}

class KnapsackBranchAndBound {
    
    // Comparator for sorting items by value/weight ratio
    static class ItemComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            return Double.compare(b.ratio, a.ratio); // Sort in descending order
        }
    }

    // Function to compute upper bound for a node
    static int bound(Node u, int n, int W, List<Item> arr) {
        if (u.weight >= W) return 0;

        int profitBound = u.profit;
        int j = u.level + 1;
        double totalWeight = u.weight;

        while (j < n && totalWeight + arr.get(j).weight <= W) {
            totalWeight += arr.get(j).weight;
            profitBound += arr.get(j).value;
            j++;
        }

        // If there's still remaining capacity, take fraction of the next item
        if (j < n) {
            profitBound += (W - totalWeight) * arr.get(j).ratio;
        }

        return profitBound;
    }

    // Function to solve 0/1 Knapsack using Branch and Bound
    static int knapsack(int W, List<Item> arr, int n) {
        // Sort items by value/weight ratio in descending order
        arr.sort(new ItemComparator());

        Queue<Node> queue = new LinkedList<>();
        Node u = new Node(-1, 0, 0, bound(new Node(-1, 0, 0, 0), n, W, arr));
        int maxProfit = 0;

        queue.add(u);

        while (!queue.isEmpty()) {
            u = queue.poll();

            if (u.level == n - 1) continue;

            // Left child (Include the item)
            Node v = new Node(u.level + 1, 
                              u.profit + arr.get(u.level + 1).value, 
                              u.weight + arr.get(u.level + 1).weight, 
                              0);

            if (v.weight <= W && v.profit > maxProfit) {
                maxProfit = v.profit;
            }

            v.bound = bound(v, n, W, arr);
            if (v.bound > maxProfit) {
                queue.add(v);
            }

            // Right child (Exclude the item)
            v = new Node(u.level + 1, u.profit, u.weight, 0);
            v.bound = bound(v, n, W, arr);

            if (v.bound > maxProfit) {
                queue.add(v);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // Number of items
        List<Item> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double weight = sc.nextDouble();
            int value = sc.nextInt();
            arr.add(new Item(weight, value));
        }

        int W = sc.nextInt(); // Knapsack capacity

        int maxProfit = knapsack(W, arr, n);
        System.out.print(maxProfit);

        sc.close();
    }
}



/*
Problem Statement:

Alex and a group of adventurous friends are setting off on a thrilling expedition to explore the mysterious Lost Valley,
a hidden paradise filled with breathtaking landscapes and ancient ruins. However, they can only carry a limited amount
of gear due to the challenging terrain.

To make the most of their journey, Alex and the team need to carefully select which items to pack in their backpacks.
Each item has a specific weight and value, and they must choose wisely to maximize the total value of their gear
while staying within the weight limit.

Help Alex and the team determine the best combination of items to take, ensuring they are well-equipped for their
adventure without exceeding the carrying capacity using the backtracking technique.

--------------------------
Input format:
--------------------------
- The first line contains an integer n, representing the number of items available for selection.
- The second line contains an integer limit, representing the maximum weight limit your group can carry in a bag.
- The next n lines contain space-separated information for each item:
    - The name of the item is a string (without spaces).
    - The weight of the item.
    - The value of the item.

--------------------------
Output format:
--------------------------
- The first line of output displays "Total value: " followed by an integer representing the total value of the selected items.
- The second line of output displays "Total weight: " followed by an integer representing the total weight of the selected items.

--------------------------
Code constraints:
--------------------------
- 1 ≤ n ≤ 8
- 1 ≤ limit ≤ 1000
- 1 ≤ string length ≤ 50
- 1 ≤ weight ≤ 100
- 1 ≤ value ≤ 10000

--------------------------
Sample test cases:
--------------------------

Input 1:
2
25
Bag 10 250
Note 15 430

Output 1:
Total value: 680
Total weight: 25

--------------------------

Input 2:
3
50
Laptop 40 10000
Bottle 10 250
Sunglasses 15 430

Output 2:
Total value: 10250
Total weight: 50

*/



// You are using Java
// You are using Java
import java.util.*;

public class Main {
    static int maxVal = 0, maxWeight = 0;
    static List<String> bestItems = new ArrayList<>();

    static void knapsack(List<Item> items, int index, int remainingWeight, int currentValue, int currentWeight, List<String> selectedItems) {
        if (index == items.size()) {
            if (currentValue > maxVal) {
                maxVal = currentValue;
                maxWeight = currentWeight;
                bestItems = new ArrayList<>(selectedItems);
            }
            return;
        }
        
        Item item = items.get(index);
        
        // Include the item if it fits
        if (remainingWeight >= item.weight) {
            selectedItems.add(item.name);
            knapsack(items, index + 1, remainingWeight - item.weight, currentValue + item.value, currentWeight + item.weight, selectedItems);
            selectedItems.remove(selectedItems.size() - 1);
        }
        
        // Exclude the item
        knapsack(items, index + 1, remainingWeight, currentValue, currentWeight, selectedItems);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int limit = sc.nextInt();
        List<Item> items = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int weight = sc.nextInt();
            int value = sc.nextInt();
            items.add(new Item(name, weight, value));
        }
        
        knapsack(items, 0, limit, 0, 0, new ArrayList<>());
        
        System.out.println("Total value: " + maxVal);
        System.out.println("Total weight: " + maxWeight);
        
        sc.close();
    }
}

class Item {
    String name;
    int weight, value;
    
    Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}



/*
Problem Statement:

Michael has been given an important task—developing a program to solve the classic 0/1 Knapsack Problem.
His goal is to help users select the most valuable combination of items while staying within a specified weight limit.

In this problem, each item has a value and a weight, and the objective is to maximize the total value
without exceeding the given weight capacity.

Michael's program should determine the maximum possible value that can be obtained by selecting the optimal set of items
using the backtracking technique.

--------------------------
Input format:
--------------------------
- The first line of input consists of an integer n, representing the number of items available for selection.
- The second line of input consists of n space-separated integers, P, which represent the values of the items.
- The third line of input consists of n space-separated integers, C, which represent the weights of the corresponding items.
- The fourth line of input consists of an integer W, representing the maximum weight limit of the knapsack.

--------------------------
Output format:
--------------------------
- The output displays the maximum value that can be obtained using the knapsack algorithm in the following format:
  "Maximum value: [maximum value]"

--------------------------
Code constraints:
--------------------------
- 1 ≤ n ≤ 15
- 1 ≤ P, W ≤ 1000
- 1 ≤ C ≤ 100

--------------------------
Sample test cases:
--------------------------

Input 1:
6
300 150 120 100 90 80
6 3 3 2 2 2
10

Output 1:
Maximum value: 490

--------------------------

Input 2:
5
60 100 120 90 80
10 20 30 40 50
50

Output 2:
Maximum value: 220

*/




// You are using Java
import java.util.*;

public class Main {
    static int knapsack(int[] values, int[] weights, int capacity, int n, int index, int currentValue, int currentWeight) {
        if (index == n || currentWeight > capacity)
            return (currentWeight <= capacity) ? currentValue : 0;
        
        // Exclude current item
        int exclude = knapsack(values, weights, capacity, n, index + 1, currentValue, currentWeight);
        
        // Include current item if within weight limit
        int include = 0;
        if (currentWeight + weights[index] <= capacity) {
            include = knapsack(values, weights, capacity, n, index + 1, 
                               currentValue + values[index], currentWeight + weights[index]);
        }
        
        return Math.max(include, exclude);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        
        for (int i = 0; i < n; i++) values[i] = sc.nextInt();
        for (int i = 0; i < n; i++) weights[i] = sc.nextInt();
        int capacity = sc.nextInt();
        
        int maxValue = knapsack(values, weights, capacity, n, 0, 0, 0);
        System.out.println("Maximum value: " + maxValue);
        
        sc.close();
    }
}




