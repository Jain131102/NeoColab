import java.util.*;
public class Prims //Lab 1
{
    static class Edge implements Comparable<Edge>{
        int u,v,wt;
        Edge(int u,int v,int wt)
        {
            this.u=u;
            this.v=v;
            this.wt=wt;
        }
        public int compareTo(Edge e)
        {
            return this.wt-e.wt;
        }
    }
    public static void prims(int adj[][],int V)
    {
        boolean visited[]=new boolean[V];
        ArrayList<Edge> ans=new ArrayList<>();
        PriorityQueue<Edge> pq=new PriorityQueue<>();

        visited[0]=true;
        int sum=0;

        for(int i=0;i<V;i++)
        {
            if(adj[0][i]>0)
            pq.offer(new Edge(0, i, adj[0][i]));
        }
        while(!pq.isEmpty())
        {
            Edge e=pq.poll();
            int u=e.u;
            int v=e.v;
            int wt=e.wt;

            if(!visited[v])
            {
                visited[v]=true;
                ans.add(e);
                sum+=wt;

                for(int i=0;i<V;i++)
                {
                    if(adj[v][i]>0 && !visited[i])
                    pq.offer(new Edge(v, i, adj[v][i]));
                }
            }
        }

            Collections.sort(ans,new Comparator<Edge>(){
                public int compare(Edge e1,Edge e2)
                {
                    if(e1.v!=e2.v)
                    return e1.v-e2.v;
                    else
                    return e1.u-e2.u;
                }
            });

            System.out.println("Minimum Spanning Cost=>"+sum);
            System.out.println("u\tv\twt");
            for(Edge e:ans)
            {
                System.out.println(e.u+"\t"+e.v+"\t"+e.wt);
            }
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        int E=sc.nextInt();

        int adj[][]=new int[V][V];
        for(int i=0;i<E;i++)
        {
            int u=sc.nextInt();
            int v=sc.nextInt();
            int wt=sc.nextInt();

            adj[u][v]=wt;
            adj[v][u]=wt;
        }

        prims(adj,V);
    }

}