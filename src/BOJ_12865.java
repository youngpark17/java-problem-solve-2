
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ_12865 {
    static int n, k, ans;
    static int[][] dp;
    static int[] w;
    static int[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n+1][k+1];
        int tw, tv;
        w = new int[n+1];
        v = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            tw = Integer.parseInt(st.nextToken());
            tv = Integer.parseInt(st.nextToken());
            v[i] = tv;
            w[i] = tw;
        }
        for (int i = 1; i <n+1; i++) {
            for (int j = 1; j <k+1; j++) {
                if(w[i]>j){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }
            }
        }
        System.out.println(dp[n][k]);

    }

}


