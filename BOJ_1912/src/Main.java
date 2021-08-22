import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int max;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        max = arr[0];
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int len = n;
        for (int i = 1; i < len; i++) {
            // 0보다 크면 더하자.
            if(arr[i]>=0){
                max = Math.max(dp[i-1]+arr[i],max);
                dp[i] = dp[i-1] +arr[i];
            }
            else{
                if(dp[i-1]+arr[i]>0){
                    dp[i] = dp[i-1] + arr[i];
                    max = Math.max(dp[i-1]+arr[i],max);
                }
                else{
                    dp[i] = 0;
                    max = Math.max(max,Math.max(dp[i-1]+arr[i],arr[i]));
                }
            }
        }
        System.out.println(max);
    }
}
