import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int q;
    static int[][] arr;
    static int[][] visited;
    static int[] l;
    static int[][] zz = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int maxIce = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        q = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new int[n][n]; //방문안했으면 0, 방문했으면 1, 방문하고 녹이는 체크까지 했다면  2
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(l).forEach(Main::firstorm);
        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j]>0){
                    totalCount+=arr[i][j];
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j]>0 && visited[i][j] == 0) {
                    int cc=0;
                    Queue<Node> que = new LinkedList<>();
                    visited[i][j] = 1;
                    Node node = new Node(i, j);
                    que.add(node);
                    while (!que.isEmpty()) {
                        Node tt = que.poll();
                        cc++;
                        for (int[] k : zz) {
                            int nx = tt.x + k[0];
                            int ny = tt.y + k[1];
                            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                if (visited[nx][ny] == 0 && arr[nx][ny]>0) {
                                    visited[nx][ny] = 1;
                                    que.add(new Node(nx, ny));
                                }
                            }
                        }
                    }
                    count = Math.max(count,cc);
                }
            }
        }
        System.out.println(totalCount);
        System.out.println(count);
    }

    public static void firstorm(int ll) {
        // 0. ll만큼 격자를 나눔. -> 재귀로 분할
        // 1. ll만큼 시계방향 회전
        if(ll!=0){
            divArr(0, 0, n, n, ll); //이거까지 체크
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt=0;
                for(int[] z : zz){
                    int nx = i+z[0];
                    int ny = j+z[1];
                    if(nx>=0 && ny>=0 && nx<n && ny<n){
                        if(arr[nx][ny]>0){
                            cnt++;
                        }
                    }
                }
                visited[i][j] = cnt;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]<3){
                    arr[i][j]-=1;
                }
                visited[i][j]=0;
            }
        }
    }
    public static void divArr(int lr,int lc, int rr,int rc,int ll){
        if((rr-lr)==(int)Math.pow(2,ll)){
            rotate(lr,lc,rr,rc);
            return;
        }
        divArr(lr,lc,(lr+rr)/2,(lc+rc)/2,ll);
        divArr(lr,(lc+rc)/2,(lr+rr)/2,rc,ll);
        divArr((lr+rr)/2,lc,rr,(lc+rc)/2,ll);
        divArr((lr+rr)/2,(lc+rc)/2,rr,rc,ll);

    }

    public static void rotate(int lr, int lc, int rr, int rc){
        int N = rr-lr;
        int[][] tmp = new int[N][N];
        int[][] tmpArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpArr[i][j] = arr[lr+i][lc+j];
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                tmp[c][N-1-r] = tmpArr[r][c];
            }
        }
        tmpArr = tmp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[lr+i][lc+j] = tmpArr[i][j];
            }
        }
    }
}

class Node{
    int x;
    int y;
    Node(int x, int y){
        this.x=x;
        this.y=y;
    }
}

