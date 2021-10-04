import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static long[] dp;
    static long[] tmpDp;
    static int module = 1_000_000_007;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        //0정보, 1전산, 2미래, 3신양 4한경 5진리 6형남 7학생
        dp = new long[8];
        tmpDp = new long[8];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            move();
            Arrays.fill(tmpDp,0);
        }

        System.out.println(dp[0]);
    }
    public static void move(){
        tmpDp[0] = (dp[1]+dp[2])%module;
        tmpDp[1] = (dp[0]+dp[3]+dp[2])%module;
        tmpDp[2] = (dp[0]+dp[1]+dp[3]+dp[4])%module;
        tmpDp[3] = (dp[1]+dp[2]+dp[4]+dp[5])%module;
        tmpDp[4] = (dp[2]+dp[3]+dp[5]+dp[6])%module;
        tmpDp[5] = (dp[3]+dp[4]+dp[7])%module;
        tmpDp[6] = (dp[4]+dp[7])%module;
        tmpDp[7] = (dp[5]+dp[6])%module;
        dp = Arrays.copyOf(tmpDp,tmpDp.length);
    }
}
