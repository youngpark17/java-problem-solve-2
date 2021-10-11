import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int p,w,c,v;
    static int[] parent;
    static class Node{
        int n1;
        int n2;
        int w;
        Node(int n1, int n2, int w){
            this.n1=n1;
            this.n2=n2;
            this.w=w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        parent = new int[p];
        for (int i = 0; i < p; i++) {
            parent[i] = i;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((node1,node2) -> node2.w-node1.w);
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Node node = new Node(a,b,c);
            pq.add(node);
        }
        boolean unioned = false;
        while(pq.isEmpty() == false && !unioned){
            Node node = pq.poll();
            int n1 = node.n1;
            int n2 = node.n2;
            union(n1,n2);
            int parentC = find(c);
            int parentV = find(v);
            if(parentC==parentV){
                System.out.println(node.w);
                break;
            }
        }

    }

    public static boolean isUnioned(int a, int b){
        return find(a) == find(b);
    }

    public static int find(int a){
        if(a==parent[a]){
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if(parentA == parentB){
            return;
        }
        if(a<b){
            parent[parentA] = parentB;
        }
        else{
            parent[parentB] = parentA;
        }

    }
}
