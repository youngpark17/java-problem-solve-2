import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static Node[] nodes;
    static boolean[] visited;
    static class Node{
        int parent;
        long cost;
        Node(int parent,long cost){
            this.parent=parent;
            this.cost=cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nodes = new Node[n+1];
        visited = new boolean[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            nodes[i] = new Node(i,Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }
        int sum = 0;
        for (int i = 1; i < n+1; i++) {
            Node node = find(i);
            if(visited[node.parent] == false){
                visited[node.parent] = true;
                sum += node.cost;
            }
        }
        if(sum<=k){
            System.out.println(sum);
        }
        else{
            System.out.println("Oh no");
        }

    }
    public static Node find(int a){
        if(a == nodes[a].parent){
            return nodes[a];
        }
        Node ret = find(nodes[a].parent);
        nodes[a].parent =ret.parent;
        return ret;
    }

    public static void union(int a, int b){
        if(a==b){
            return;
        }
        Node aNode = find(nodes[a].parent);
        Node bNode = find(nodes[b].parent);

        aNode.parent = bNode.parent;

        aNode.cost = Math.min(bNode.cost ,aNode.cost);
        bNode.cost = Math.min(bNode.cost ,aNode.cost);
    }

}
