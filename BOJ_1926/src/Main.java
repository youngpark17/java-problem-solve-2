import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[][] flags=  {{-1,0},{1,0},{0,-1},{0,1}};
    static int count=0;
    static int max=0;
    static int n,m;
    static int s = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = tmp[0];
        m = tmp[1];
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n ; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && map[i][j] == 1){
                    s=1;
                    dfs(i,j);
                    count+=1;
                }
            }
        }
        System.out.println(count);
        System.out.println(max);

    }
    public static void dfs(int r, int c){
        visited[r][c] = true;
        for(int[] flag : flags){
            int nr = r + flag[0];
            int nc = c + flag[1];
            if(nr>=0 && nc>=0 && nr<n && nc<m){
                if(!visited[nr][nc] && map[nr][nc] == 1){
                    s+=1;
                    dfs(nr,nc);
                }
            }
        }
        max = Math.max(max,s);
    }


}
