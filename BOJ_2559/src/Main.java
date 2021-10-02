import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        int[] dp = new int[n+1];
        st = new StringTokenizer(br.readLine());
        arr[1] = Integer.parseInt(st.nextToken());
        dp[1] = arr[1];
        for (int i = 2; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i]+dp[i-1];
        }

        //answer = Math. max(dp[i+k]-dp[i])
        int answer = 0;
        for (int i = k; i< n+1; i++) {
            int a= dp[i] - dp[i-k];
            answer = Math.max(answer,a);
        }
        System.out.println(answer);
    }
}
