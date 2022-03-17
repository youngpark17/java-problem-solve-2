public class Solution {
    final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(7));
    }

    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2])%MOD;
        }
        return dp[n];
    }
}
