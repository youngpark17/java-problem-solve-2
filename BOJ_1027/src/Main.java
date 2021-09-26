import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arrs;
    static int[] ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arrs = new int[n+1];
        ans = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            arrs[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < n+1; i++) {
            double max = (double)-999999999.99999;
            for (int j = i+1; j < n+1; j++) {
                double angle = (double)(arrs[i]-arrs[j])/(i-j);
                if(angle > max){
                    ans[i]+=1;
                    ans[j]+=1;
                    max = angle;
                }
            }
        }
        System.out.println(Arrays.stream(ans).max().orElse(0));

    }
}