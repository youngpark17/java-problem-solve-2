import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] costs;
    static int[] dp;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        costs = new int[n+1];
        dp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
        //int newCost = dp[a]*(n/a)+dp[n%a];
        dp[1] = costs[1];
        for (int a = 2; a < n+1; a++) {
            int partMax = 0;
            for (int i = 1; i < a; i++) {
                partMax = Math.max(partMax,dp[i]*(a/i)+dp[a%i]);
            }
            dp[a] = Math.max(costs[a],partMax);
        }
        System.out.println(dp[n]);

    }
}
