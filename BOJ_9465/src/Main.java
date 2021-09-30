import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T,n;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(T-->0){
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n];
            dp = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            if(n>1){
                dp[0][1] = Math.max(dp[0][0],dp[1][0]+arr[0][1]);
                dp[1][1] = Math.max(dp[1][0],dp[0][0]+arr[1][1]);
                for (int i = 2; i < n; i++) {
                    dp[0][i] = Math.max(dp[1][i-2],dp[1][i-1])+arr[0][i];
                    dp[1][i] = Math.max(dp[0][i-2],dp[0][i-1])+arr[1][i];
                }
            }
            bw.append(Math.max(dp[0][n-1],dp[1][n-1])+"\n");

        }
        bw.flush();
        bw.close();

    }
}
