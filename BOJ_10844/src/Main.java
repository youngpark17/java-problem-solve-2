import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static long[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[11][n+1];
        for (int i = 0; i < 11; i++) {
            if(i!=0 && i!=10){
             dp[i][0] = 1;
            }
        }
        //dp[a][b] = dp[a-1][b-1] + dp[a+1][b+1] +dp[a][b-1]
        for (int b = 1; b < n; b++) {
            for (int a = 0; a < 10; a++) {
                if(a==0){
                    dp[a][b] = (dp[a+1][b-1]);
                }
                else{
                    dp[a][b] = (dp[a-1][b-1] + dp[a+1][b-1])%1_000_000_000;
                }

            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum+=dp[i][n-1];
            sum%=1_000_000_000;
        }
        System.out.println(sum);
    }
}
