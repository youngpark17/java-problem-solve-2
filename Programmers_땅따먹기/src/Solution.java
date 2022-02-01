public class Solution {
    int[][] dp;
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] lan = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        System.out.println(so.solution(lan));
    }
    int solution(int[][] land) {
        int answer = 0;
        int len = land.length;
        dp = new int[len][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        for (int i = 1; i < len; i++) {
            dp[i][0] = land[i][0] +getMax(dp[i-1][1], dp[i-1][2], dp[i-1][3]);
            dp[i][1] = land[i][1] +getMax(dp[i-1][0], dp[i-1][2], dp[i-1][3]);
            dp[i][2] = land[i][2] +getMax(dp[i-1][1], dp[i-1][0], dp[i-1][3]);
            dp[i][3] = land[i][3] +getMax(dp[i-1][1], dp[i-1][2], dp[i-1][0]);
        }

        return Math.max(getMax(dp[len-1][0],dp[len-1][1],dp[len-1][2]),dp[len-1][3]);
    }
    private int getMax(int a, int b, int c){
        return Math.max(c,Math.max(a,b));
    }
}
