public class Solution {
    public static void main(String[] args) {

    }

    public long solution(int numOfStairs) {
        long[] dp = new long[71];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        dp[4] = 7;
        for (int i = 5; i <= numOfStairs; i++) {
            dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
        }
        long answer = dp[numOfStairs];
        return answer;
    }
}
