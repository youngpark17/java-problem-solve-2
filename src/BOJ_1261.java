import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1261 {
    static int n,m;
    static int[][] map;
    static int[][] countMap;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        countMap = new int[n][m];
        for(int i=0; i<n;i++){
            Arrays.fill(countMap[i],(1<<31)-1);
        }
        for(int i=0; i<n; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<m; j++){
                map[i][j] = tmp[j]-'0';
            }
        }
        //bfs로 이동하면서 체크하고 방문했더라도 더 적게 부수고 거기에 도달가능하다면 체크.
        countMap[0][0]=0;
        bfs(0,0);
        System.out.println(countMap[n-1][m-1]);


    }
    public static void bfs(int x, int y){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x,y,0));
        while(!que.isEmpty()){
            Node t = que.poll();
             for(int i=0; i<4; i++){
                int nx = t.x+dx[i];
                int ny = t.y+dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<m){
                    if(map[nx][ny]==1){
                        if(countMap[nx][ny]>t.c+1){
                            countMap[nx][ny] = t.c+1;
                            que.add(new Node(nx,ny,t.c+1));
                        }
                    }
                    else{
                        if(countMap[nx][ny]>t.c){
                            countMap[nx][ny] = t.c;
                            que.add(new Node(nx,ny,t.c));
                        }
                    }

                }
            }
        }
    }
}

class Node{
    int x;
    int y;
    int c;
    Node(int x, int y, int c){
        this.x=x;
        this.y=y;
        this.c=c;
    }
}
