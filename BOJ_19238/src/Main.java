import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k; //k는 연료
    static int[][] map;
    static int cx;
    static int cy;
    static Node[] node;
    static boolean[] die;
    static int cnt;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt=m;
        map = new int[n][n];
        die = new boolean[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        cx = Integer.parseInt(st.nextToken())-1;
        cy = Integer.parseInt(st.nextToken())-1;
        node = new Node[m];
        for (int i = 0; i < m; i++) {
            st=new StringTokenizer(br.readLine());
            int x1=  Integer.parseInt(st.nextToken())-1;
            int y1=  Integer.parseInt(st.nextToken())-1;
            int x2=  Integer.parseInt(st.nextToken())-1;
            int y2=  Integer.parseInt(st.nextToken())-1;
            node[i] = new Node(x1,y1,x2,y2);
        }
        int min = 1000;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        while(cnt!=0){
            //최단 거리 구해서 승객 태우자.
            for(int i=0; i<m; i++){
                if(!die[i]){
                    int tmin = bfs(i);
                    if(tmin<0){
                        System.out.println(-1);
                        System.exit(0);
                    }
                    Pair pp = new Pair(node[i].sx,node[i].sy,tmin);
                    pp.num=i;
                    pq.add(pp);
                }
            }
            //pq에서 꺼내서 이동하자.
            Pair person = pq.poll();
            if(person.c>k){
                System.out.println(-1);
                System.exit(0);
                break;
            }
            k-=person.c;
            int num = person.num;
            //node[num].fx,fy로 이동하자.
            int dis = getDistance(num);
            if(dis<0){
                System.out.println(-1);
                System.exit(0);
            }
            if(dis>k){
                System.out.println(-1);
                System.exit(0);
                break;
            }
            k+=dis; //2배가 충전된다.
            die[num] = true;
            cnt--;
            cx = node[num].fx;
            cy = node[num].fy;
            pq.clear();
        }
        System.out.println(k);

    }

    public static int getDistance(int nodeNum){
        int ex = node[nodeNum].fx;
        int ey = node[nodeNum].fy;
        int ssx = node[nodeNum].sx;
        int ssy = node[nodeNum].sy;
        Pair p = new Pair(ssx,ssy,0);
        boolean[][] visited = new boolean[n][n];
        visited[ssx][ssy]= true;
        Queue<Pair> que = new LinkedList<>();
        que.add(p);
        while(!que.isEmpty()){
            Pair t = que.poll();
            if(t.x==ex && t.y==ey){
                return t.c;
            }
            for(int i=0; i<4; i++){
                int nx = t.x+dx[i];
                int ny = t.y+dy[i];
                if(nx>=0 && ny>=0&& nx<n && ny<n){
                    if(!visited[nx][ny]&&map[nx][ny]==0){
                        visited[nx][ny] = true;
                        que.add(new Pair(nx,ny,t.c+1));
                    }
                }

            }
        }
        return -1;
    }

    public static int bfs(int nodeNumber) {
        int ex = node[nodeNumber].sx;
        int ey = node[nodeNumber].sy;
        Pair p = new Pair(cx, cy, 0);
        boolean[][] visited = new boolean[n][n];
        visited[cx][cy] = true;
        Queue<Pair> que = new LinkedList<>();
        que.add(p);
        while (!que.isEmpty()) {
            Pair t = que.poll();
            if (t.x == ex && t.y == ey) {
                return t.c;
            }
            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visited[nx][ny] && map[nx][ny]==0) {
                        visited[nx][ny] = true;
                        que.add(new Pair(nx, ny, t.c + 1));
                    }
                }
            }
        }
        return -1;

    }
}


class Pair implements Comparable<Pair>{
    int x;
    int y;
    int c;
    int num;
    Pair(int x, int y, int c){
        this.x=x;
        this.y=y;
        this.c=c;
        num=-1;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.c-o.c!=0){
            return this.c-o.c;
        }
        if(this.x-o.x!=0){
            return this.x-o.x;
        }
        return this.y-o.y;

    }
}

class Node{
    int sx;
    int sy;
    int fx;
    int fy;

    Node(int x,int y, int x1, int y1){
        sx=x;
        sy=y;
        fx=x1;
        fy=y1;
    }
}
