import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9251 {
    static char[] c1;
    static char[] c2;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        c1 = br.readLine().toCharArray();
        c2 = br.readLine().toCharArray();
        int s1 = c1.length;
        int s2 = c2.length;
        dp = new int[s1+1][s2+1];
        for(int i=1; i<=s1; i++){
            for(int j=1; j<=s2; j++){
                if(c1[i-1]==c2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[s1][s2]);
    }
}
