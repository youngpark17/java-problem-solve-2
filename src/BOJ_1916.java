import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1916 {
    static int n,m;
    static List<Node2>[] list;
    static int[] distance;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new List[n+1];
        for(int i=1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }
        ans=0;
        distance = new int[n+1];
        Arrays.fill(distance,(1<<31)-1);
        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from,to,cost;
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost =Integer.parseInt(st.nextToken());
            list[from].add(new Node2(to,cost));
        }
        StringTokenizer st= new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        PriorityQueue<Node2> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
        pq.add(new Node2(from,0));
        int cnt=0;
        while(!pq.isEmpty()){
            Node2 t = pq.poll();
            if(distance[t.to]>t.cost){
                distance[t.to] = t.cost;
                for(Node2 tt : list[t.to]){
                    pq.add(new Node2(tt.to,tt.cost+t.cost));
                }
            }
        }
        System.out.println(distance[to]);
    }
}


class Node2{
    int to;
    int cost;

    Node2(int to, int cost){
        this.to=to;
        this.cost=cost;
    }
}
