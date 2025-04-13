// 🌍 Problem Statement: Counting Connected Terrain Components

// Liam, a scientist, is analyzing a 2D grid where each cell represents a type of terrain (denoted by a character).
// He needs to determine the number of **connected components** in the grid. A connected component is formed by 
// **adjacent cells (horizontally or vertically)** that have the **same terrain type**.

// Your task is to write a program to help Liam count how many such connected components exist in the grid.

// 📥 Input Format:
// • The first line contains an integer `n` — the number of rows in the grid.
// • The second line contains an integer `m` — the number of columns in the grid.
// • The next `n` lines each contain a string of `m` lowercase characters, where each character represents a terrain type at that cell.

// 📤 Output Format:
// • Print a single integer — the number of connected components in the matrix.

// 🔒 Constraints:
// • 1 ≤ n, m ≤ 10
// • Terrain types are lowercase letters (e.g., 'a', 'b', ..., 'j')

// 🧪 Sample Test Case 1:
// Input:
// 3
// 5
// aabba
// aabba
// aaaca

// Output:
// 4

// 🧪 Sample Test Case 2:
// Input:
// 4
// 4
// aaaa
// abba
// abba
// aaaa

// Output:
// 2

// 🧠 Explanation for Sample 2:
// Grid:
// a a a a
// a b b a
// a b b a
// a a a a
//
// Component 1: All 'a' cells form one large connected component.
// Component 2: All 'b' cells in the center form the second component.
// So the total number of components is 2.


import java.util.Scanner;

 class ConnectedComponents {

    static final int MAX_ROW = 500;
    static final int MAX_COL = 500;
    static boolean[][] visited = new boolean[MAX_ROW][MAX_COL];

    // Function to check if it's safe to visit the cell
    static boolean isSafe(char[][] M, int row, int col, char c, int n, int l) {
        return (row >= 0 && row < n) && (col >= 0 && col < l) && (M[row][col] == c && !visited[row][col]);
    }

    // Depth First Search (DFS) function
    static void DFS(char[][] M, int row, int col, char c, int n, int l) {
        int[] rowNbr = {-1, 1, 0, 0};
        int[] colNbr = {0, 0, 1, -1};

        visited[row][col] = true;

        for (int k = 0; k < 4; ++k) {
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], c, n, l)) {
                DFS(M, row + rowNbr[k], col + colNbr[k], c, n, l);
            }
        }
    }

    // Function to count the number of connected components
    static int connectedComponents(char[][] M, int n, int l) {
        int connectedComp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                if (!visited[i][j]) {
                    char c = M[i][j];
                    DFS(M, i, j, c, n, l);
                    connectedComp++;
                }
            }
        }
        return connectedComp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        char[][] M = new char[MAX_ROW][MAX_COL];

        // Input the matrix
        for (int i = 0; i < n; i++) {
            String row = sc.next();
            M[i] = row.toCharArray();  // Convert string to character array
        }

        // Get and print the number of connected components
        System.out.println(connectedComponents(M, n, l));
        sc.close();
    }
}

