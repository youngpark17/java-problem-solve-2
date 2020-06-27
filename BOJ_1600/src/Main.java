import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int w,h;
    static int[][] map;
    static int[] dx = {-1,1,0,0,-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {0,0,-1,1,-1,1,-2,2,-2,2,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        //헷갈리니까 바꾸자.
        int tmp = w;
        w = h;
        h= tmp;
        map = new int[w][h];
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < h; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int t = bfs();
        if (t==(1<<31)-1){
            System.out.println(-1);
        }
        else{
            System.out.println(t);
        }
    }
    public static int bfs(){
        boolean[][][] visited = new boolean[k+1][w][h]; //k번째로 이곳에 방문했는데 나보다 num이 작다면?

        Node start = new Node(0,0,0,0); //time이 k가 되면 더이상 x;
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        int ret=(1<<31)-1;
//        1
//        3 4
//        0 0 0
//        1 1 0
//        1 1 1
//        1 0 0
        //정답 5
        while(!que.isEmpty()){
            Node t = que.poll();
            if(t.x==w-1 && t.y==h-1) {
                ret=Math.min(ret,t.num);
                continue;
            }
            for (int i = 0; i < 12; i++) {
                int nx = t.x+dx[i];
                int ny = t.y+dy[i];
                if(nx>=0 && ny>=0 && nx<w && ny<h){
                    if(map[nx][ny]==0){
                        if(i>=4 &&t.time<k){
                            if(!visited[t.time+1][nx][ny]){
                                visited[t.time+1][nx][ny] = true;
                                que.add(new Node(nx,ny,t.time+1,t.num+1));
                            }
                        }
                        else if(i<4 && !visited[t.time][nx][ny]){
                            visited[t.time][nx][ny] = true;
                            que.add(new Node(nx,ny,t.time,t.num+1));
                        }
                    }

                }
            }
        }
        return ret;

    }
}

class Node{//Monkey;
    int x;
    int y;
    int time;
    int num;
    Node(int x, int y, int time, int num){
        this.x=x;
        this.y=y;
        this.time=time;
        this.num=num;
    }
}
