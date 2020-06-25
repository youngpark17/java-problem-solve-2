import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[] visited;
    static List<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        graph = new List[n+1];
        for (int i = 1; i <n+1 ; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < m+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());
            graph[k].add(k2);
            graph[k2].add(k);
        }
        visited[1] = true;
        int ans = bfs(1);

        System.out.println(ans);
    }
    public static int bfs(int p){
        int cnt=0;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(p,0));
        while(!que.isEmpty()){
            Node t = que.poll();
            if(t.depth>=2){
                break;
            }
            for(int k : graph[t.x]){
                if(!visited[k]){
                    visited[k] = true;
                    cnt++;
                    que.add(new Node(k,t.depth+1));
                }
            }
        }
        return cnt;
    }
}

class Node{
    int x;
    int depth;

    Node(int x, int depth){
        this.x=x;
        this.depth=depth;
    }
}
