import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,e,a,b;
    static class Node implements Comparable<Node>{
        int v;
        int cost;
        Node(int v, int cost){
            this.v=v;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
    static List<Node>[] listArr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        listArr = new List[n+1];
        for (int i = 0; i < n+1; i++) {
            listArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            listArr[v1].add(new Node(v2,d));
            listArr[v2].add(new Node(v1,d));
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        boolean flag = false;
        int d1 = getDistance(1,a); // 1->v1
        int d2 = getDistance(1,b); // 1->v2
        if(d1 == Integer.MAX_VALUE && d2 == Integer.MAX_VALUE){
            flag = true;
        }
        int d3 = getDistance(a,b); // v1->v2 v2->v1
        if(d3==Integer.MAX_VALUE){
            flag = true;
        }
        int d4=0;
        int d5=0;
        if(flag == false){
            d4 = getDistance(a,n); // v1->e
            d5 = getDistance(b,n); // v2->e
        }
        if(d4 ==Integer.MAX_VALUE && d5 == Integer.MAX_VALUE){
            flag =true;
        }
        if(flag == true){
            System.out.println(-1);
        }
        else{
            int ans = Math.min(d1+d3+d5,d2+d3+d4);
            System.out.println(ans);
        }


    }
    public static int getDistance(int s, int e){
        int[] distanceMemo = new int[n+1];
        Arrays.fill(distanceMemo,Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));
        distanceMemo[s] = 0;
        while(pq.isEmpty() == false){
            Node node = pq.poll();
            int v = node.v;
            int cost = node.cost;
            List<Node> nodeList = listArr[v];
            for(Node node2 : nodeList){
                if(cost+node2.cost<distanceMemo[node2.v]){
                    distanceMemo[node2.v] = cost+node2.cost;
                    pq.add(new Node(node2.v,distanceMemo[node2.v]));
                }
            }
        }
        return distanceMemo[e];
    }
}
