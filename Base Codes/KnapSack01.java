
import java.util.Scanner;

public class KnapSack01 { //Lab 7

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();

        int wt[] = new int[n];
        int val[] = new int[n];

        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.println(helper(0,W,wt,val,n));

    }
    public static int helper(int i,int W,int wt[],int val[],int n) {
        if(i>=n)
        return 0;

        int include=0;
        if(wt[i]<=W)
        include+=helper(i+1,W-wt[i],wt,val,n)+val[i];
        int exclude=helper(i+1,W,wt,val,n);

        return Math.max(include,exclude);
    }
}
