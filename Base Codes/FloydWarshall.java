
import java.util.*;

public class FloydWarshall { //Lab 5

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int arr[][] = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(arr[i][k]!=999 && arr[k][j]!=999)
                    arr[i][j]=Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }


        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(arr[i][j]==999?"INF ":arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}
