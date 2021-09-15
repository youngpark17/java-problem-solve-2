import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] arr;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,1,-1,0};
    static int cnt = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        arr = new int[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            //(x1,y1) (x2,y2) -> (y1,x1),(y2,x2)
            for (int j = y1; j < y2; j++) {
                for (int l = x1; l < x2; l++) {
                    arr[j][l] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 0){
                    dfs(i,j);
                    list.add(cnt);
                    cnt=0;
                }
            }
        }
        System.out.println(list.size());
        list.stream().sorted().forEach(a->System.out.print(a+" "));

    }

    public static void dfs(int a, int b){
        arr[a][b] = 2;
        cnt++;
        for (int i = 0; i < 4; i++) {
            int na = a+dx[i];
            int nb = b+dy[i];
            if(isRange(na,nb) == true){
                dfs(na,nb);
            }
        }

    }
    public static boolean isRange(int a,int b){
        if(a<0 || b<0 || a>=m || b>=n){
            return false;
        }
        if(arr[a][b] == 0){
            return true;
        }
        return false;
    }
}
