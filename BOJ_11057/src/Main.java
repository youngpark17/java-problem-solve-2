import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static long[][] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new long[10][n + 1];
        // dp[a(x)][b] = dp[a(x)][b-1]+...+dp[a9][b-1]

        for (int i = 0; i < 10; i++) {
            nums[i][0] = 10 - i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                int sum = 0;
                for (int k = j; k <= 9; k++) {
                    sum += (nums[k][i - 1]%10007);
                }
                nums[j][i] = sum;
            }
        }

        System.out.println(nums[0][n-1]%10007);
    }


}
