import java.util.*;
public class FractionalKnapSack { //Lab 3
    static class Pair{
        int wt,val;
        double ratio;

        Pair(int wt,int val)
        {
            this.wt=wt;
            this.val=val;
            this.ratio=(double)this.val/this.wt;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int W=sc.nextInt();

        int wt[]=new int[n];
        int val[]=new int[n];

        for(int i=0;i<n;i++)
        wt[i]=sc.nextInt();
        
        for(int i=0;i<n;i++)
        val[i]=sc.nextInt();

        Pair bag[]=new Pair[n];
        for(int i=0;i<n;i++)
        {
            bag[i]=new Pair(wt[i],val[i]);
        }
        Arrays.sort(bag,new Comparator<Pair>() {
            public int compare(Pair p1,Pair p2)
            {
                return Double.compare(p2.ratio, p1.ratio);
            }
        });

        double profit=0;
        for(int i=0;i<n;i++)
        {
            Pair p=bag[i];
            int w=p.wt;
            int v=p.val;
            if(w<=W)
            {
                profit+=v;
                W-=w;
            }
            else
            {
                profit+=v*p.ratio;
                break;
            }
        }

        System.out.printf("%.2f",profit);


    }
}
