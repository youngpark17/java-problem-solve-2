import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static int T;
    static final int num = 1_000_001;
    static int[] numSum;
    static long[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        numSum = new int[num];
        dp = new long[num];
        Arrays.fill(numSum,1);
        //8은 1,2,4,8
        for (int i = 2; i < num; i++) {
            for (int j = 1; i*j < num; j++) {
                numSum[i*j] +=i; //i*j보댜 작은약수의 합을 저장
            }
        }
        for (int i = 1; i < num; i++) {
            dp[i] += dp[i-1]+numSum[i];
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-->0){
            int k = Integer.parseInt(br.readLine());
            bw.append(dp[k]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
