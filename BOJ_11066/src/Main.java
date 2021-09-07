import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[][] dp;
    static int[] cost;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            cost = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }
            sum = new int[n + 1];
            dp = new int[n + 1][n + 1]; //dp[i][j] -> 구간i부터 j까지를 합칠때 최소 비용
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 1; i < n + 1; i++) {
                sum[i] = sum[i - 1] + cost[i];
            }
            bw.append(dpf(1, n) + "\n");
        }
        bw.flush();
        bw.close();

    }

    static int dpf(int tx, int ty) {
        if (dp[tx][ty] != Integer.MAX_VALUE) {
            return dp[tx][ty];
        }
        if (tx == ty)
            return dp[tx][ty] = 0;

        if (tx + 1 == ty)
            return dp[tx][ty] = cost[tx] + cost[ty];

        for (int mid = tx; mid < ty; mid++) {
            int left = dpf(tx, mid);
            int right = dpf(mid + 1, ty);
            dp[tx][ty] = Math.min(dp[tx][ty], left + right);
        }

        return dp[tx][ty] += sum[ty] - sum[tx - 1];
    }

}
