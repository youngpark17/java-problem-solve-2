import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static int t;
    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            int max = Integer.MIN_VALUE;
            for (int i = 2; i < n; i++) {
                max = Math.max(Math.abs(arr[i] - arr[i - 2]), max);
            }
            bw.append(max + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
