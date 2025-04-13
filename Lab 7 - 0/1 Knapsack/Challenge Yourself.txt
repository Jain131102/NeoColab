/*
 * Problem Statement:
 *
 * Ragul is interested in solving the knapsack problem. He has a list of n items, each with a weight and a value.
 * Ragul wants to determine the maximum value he can obtain by selecting a combination of items to fit into his knapsack, which has a maximum weight capacity.
 *
 * Help Ragul write a program that calculates the following:
 *
 * 1. Calculate and print the sum of all n values.
 * 2. Calculate and print the average value of all n values with exactly 2 decimal places.
 * 3. Calculate and print the maximum value that can be obtained by selecting a combination of items to fit into the knapsack without exceeding its capacity.
 *
 * Note: Use the 0/1 knapsack (backtracking technique) method to solve the problem.
 *
 * Input format:
 * - The first line contains an integer n, the number of items.
 * - The next line contains n space-separated integers, the weights of the items.
 * - The next line contains n space-separated integers, the values of the items.
 * - The last line contains an integer C, the maximum weight capacity of the knapsack.
 *
 * Output format:
 * - The output consists of the following in each line:
 *   1. The sum of all n values as an integer.
 *   2. The average value of all n values with exactly 2 decimal places.
 *   3. The maximum value that can be obtained as an integer.
 *
 * Code constraints:
 * 1 ≤ n ≤ 10
 * 1 ≤ Weight of each item ≤ 100
 * 1 ≤ Value of each item ≤ 100
 * 1 ≤ C ≤ 30
 *
 * Sample test cases:
 * Input 1:
 * 3
 * 10 15 20
 * 45 65 70
 * 30
 *
 * Output 1:
 * Sum of values: 180
 * Average of values: 60.00
 * Maximum amount: 115
 *
 * Input 2:
 * 4
 * 3 4 5 6
 * 8 10 12 15
 * 12
 *
 * Output 2:
 * Sum of values: 45
 * Average of values: 11.25
 * Maximum amount: 30
 *
 * Explanation:
 * - We first compute the sum of values of the items.
 * - Then, we calculate the average of the values.
 * - Finally, we apply the 0/1 knapsack approach to determine the maximum value that can be obtained without exceeding the knapsack's weight limit.
 */





// You are using Java
import java.util.Scanner;

class KnapsackSolver {
    
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int bruteForce(int[] w, int[] v, int n, int capacity) {
        int maxAmount = 0;
        int combinations = 1 << n; // Total possible combinations of items

        for (int i = 0; i < combinations; i++) {
            int currentWeight = 0;
            int currentAmount = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentWeight += w[j];
                    currentAmount += v[j];
                }
            }

            if (currentWeight <= capacity) {
                maxAmount = max(maxAmount, currentAmount);
            }
        }

        return maxAmount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] w = new int[n];
        int[] v = new int[n];
        
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
        }

        int sum = 0;
        for (int value : v) {
            sum += value;
        }

        double average = (double)sum / n;

        System.out.println("Sum of values: " + sum);
        System.out.printf("Average of values: %.2f\n", average);

        int capacity = scanner.nextInt();

        int maxAmount = bruteForce(w, v, n, capacity);
        System.out.println("Maximum amount: " + maxAmount);

        scanner.close();
    }
}


/*
 * Problem Statement:
 *
 * A shipping company needs to load valuable cargo onto a limited-capacity ship. Each cargo item has:
 * 1. A weight (how heavy the item is).
 * 2. A value (the profit from shipping the item).
 * 
 * The goal is to maximize the total value of the selected cargo while ensuring that the total weight does not exceed the ship's maximum capacity.
 *
 * The company wants an efficient algorithm using Branch and Bound to avoid unnecessary calculations and optimize cargo selection.
 *
 * Input format:
 * - The first line of input consists of an integer n, representing the number of cargo items.
 * - The second line of input consists of n space-separated integers, P, representing the value of each cargo item.
 * - The third line of input consists of n space-separated integers, C, representing the weight of each cargo item.
 * - The fourth line of input consists of an integer W, representing the ship’s maximum weight capacity.
 *
 * Output format:
 * - The output displays a single integer representing the maximum possible value that can be loaded onto the ship in the format:
 *   "Maximum value: [maximum value]"
 *
 * Code constraints:
 * 1 ≤ n ≤ 15
 * 1 ≤ P, W ≤ 1000
 * 1 ≤ C ≤ 100
 *
 * Sample test cases:
 * Input 1:
 * 6
 * 300 150 120 100 90 80
 * 6 3 3 2 2 2
 * 10
 *
 * Output 1:
 * Maximum value: 490
 *
 * Input 2:
 * 5
 * 60 100 120 90 80
 * 10 20 30 40 50
 * 50
 *
 * Output 2:
 * Maximum value: 220
 *
 * Explanation:
 * - The shipping company is tasked with maximizing the value of the cargo they load onto the ship, considering both the value and weight constraints.
 * - The goal is to select the optimal set of cargo items that will maximize the total value without exceeding the maximum weight capacity of the ship.
 * - The solution can be implemented using a Branch and Bound approach to prune unnecessary calculations.
 */




import java.util.*;

class Item {
    int weight, value;
    double ratio;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = (double) value / weight;
    }
}

// Node class for Branch and Bound Tree
class Node {
    int level, profit, weight;
    double bound;

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }
}

 class KnapsackBranchAndBound {
    
    // Function to calculate the upper bound of the profit in subtree
    static double bound(Node u, int n, int W, List<Item> arr) {
        if (u.weight >= W) return 0;
        
        double bound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + arr.get(j).weight <= W) {
            totalWeight += arr.get(j).weight;
            bound += arr.get(j).value;
            j++;
        }

        // If there's still remaining capacity, take fraction of next item
        if (j < n) {
            bound += (W - totalWeight) * arr.get(j).ratio;
        }

        return bound;
    }

    // Function to implement the branch and bound approach
    static int knapsack(int W, List<Item> arr, int n) {
        // Sort items by value/weight ratio in descending order
        arr.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        Queue<Node> queue = new LinkedList<>();
        Node u, v;
        
        // Root node initialization
        u = new Node(-1, 0, 0, bound(new Node(-1, 0, 0, 0), n, W, arr));
        int maxProfit = 0;

        queue.add(u);

        while (!queue.isEmpty()) {
            u = queue.poll();
            
            if (u.level == n - 1) continue; // If all items considered, skip

            // Left child (Include item)
            v = new Node(u.level + 1, 
                         u.profit + arr.get(u.level + 1).value, 
                         u.weight + arr.get(u.level + 1).weight, 
                         0);

            if (v.weight <= W && v.profit > maxProfit) 
                maxProfit = v.profit;

            v.bound = bound(v, n, W, arr);
            if (v.bound > maxProfit) 
                queue.add(v);

            // Right child (Exclude item)
            v = new Node(u.level + 1, u.profit, u.weight, 0);
            v.bound = bound(v, n, W, arr);

            if (v.bound > maxProfit) 
                queue.add(v);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Number of items
        List<Item> arr = new ArrayList<>();

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int weight = sc.nextInt();
            arr.add(new Item(weight, values[i]));
        }

        int W = sc.nextInt(); // Knapsack capacity

        int result = knapsack(W, arr, n);
        System.out.println("Maximum value: " + result);
        sc.close();
    }
}



/*
 * Problem Statement:
 *
 * A courier company wants to load valuable packages into a delivery truck that has a weight limit. 
 * Each package has a weight and a monetary value. The goal is to maximize the total value of the packages loaded into the truck 
 * while ensuring that the total weight does not exceed the truck’s capacity.
 *
 * The company must decide which packages to include using a Branch and Bound approach to find the most profitable selection.
 *
 * Input format:
 * - The first line contains two integers n (the number of packages) and W (Maximum weight capacity of the truck), separated by a space.
 * - The second line contains n integers, representing the weights of the n packages separated by a space.
 * - The third line contains n integers, representing the values of the n packages separated by a space.
 *
 * Output format:
 * - The output prints a single integer representing the maximum total value that can be obtained within the weight limit.
 *
 * Code constraints:
 * 1 ≤ n ≤ 100 (Number of packages)
 * 1 ≤ W ≤ 1000 (Weight Capacity of the truck)
 * 1 ≤ weight of item ≤ 1000 (Weight of each package)
 * 1 ≤ value of item ≤ 1000 (Value of each package)
 *
 * Sample test cases:
 * Input 1:
 * 5 100
 * 10 20 30 40 50
 * 60 100 120 140 180
 *
 * Output 1:
 * 420
 *
 * Input 2:
 * 4 50
 * 2 8 12 20
 * 40 60 80 100
 *
 * Output 2:
 * 280
 *
 * Explanation:
 * - The courier company is tasked with maximizing the value of packages loaded into the truck, 
 *   considering both the value and weight constraints.
 * - The solution should be implemented using a Branch and Bound approach to find the most profitable selection of packages.
 */




import java.util.*;

class Item {
    int weight, value, index;
    double ratio;

    public Item(int weight, int value, int index) {
        this.weight = weight;
        this.value = value;
        this.index = index;
        this.ratio = (double) value / weight;
    }
}

class Node {
    int level, profit, weight;
    double bound;

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }
}

class KnapsackBranchAndBound {
    
    // Comparator for sorting items based on value/weight ratio
    static class ItemComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            return Double.compare(b.ratio, a.ratio); // Descending order
        }
    }

    // Function to compute upper bound for a node
    static double bound(Node u, int n, int W, List<Item> arr) {
        if (u.weight >= W) return 0;

        double bound = u.profit;
        int j = u.level + 1, totalWeight = u.weight;

        while (j < n && totalWeight + arr.get(j).weight <= W) {
            totalWeight += arr.get(j).weight;
            bound += arr.get(j).value;
            j++;
        }

        // If there's still remaining capacity, take fraction of the next item
        if (j < n) {
            bound += (W - totalWeight) * arr.get(j).ratio;
        }

        return bound;
    }

    // Function to solve 0/1 Knapsack using Branch and Bound
    static int knapsack(int n, int W, List<Item> arr) {
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
        int W = sc.nextInt();  // Weight capacity

        List<Item> arr = new ArrayList<>();

        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            arr.add(new Item(weights[i], value, i));
        }

        int maxProfit = knapsack(n, W, arr);
        System.out.println(maxProfit);

        sc.close();
    }
}




