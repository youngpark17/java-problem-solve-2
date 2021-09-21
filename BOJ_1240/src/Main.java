import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int n, m;
    static List<Node>[] nodeList;
    static boolean[] visited;
    static long[][] dis;

    static class Node {
        int number;
        long d;

        public Node(int number, long d) {
            this.number = number;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodeList = new List[n + 1];
        visited = new boolean[n + 1];
        dis = new long[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,c));
            nodeList[b].add(new Node(a,c));
            dis[a][b] = c;
            dis[b][a] = c;
        }
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(visited, false);
            bfs(i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.append(dis[a][b] + "\n");
        }
        bw.flush();
        bw.close();

    }

    public static void bfs(int start) {
        Node node = new Node(start, 0);
        Queue<Node> que = new LinkedList<>();
        visited[start] = true;
        que.add(node);
        while (que.isEmpty() == false) {
            Node q = que.poll();
            dis[start][q.number] = q.d;
            dis[q.number][start] = q.d;
           for(Node n : nodeList[q.number]){
               if(visited[n.number] == false){
                   visited[n.number] = true;
                   que.add(new Node(n.number,q.d+n.d));
               }
           }
        }
    }
}