import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long sum = getRGB(r, g, b);
                bw.append(getStr(sum));
            }
            if (i != n - 1) {
                bw.append("\n");
            }
        }
        bw.flush();
        bw.close();

    }

    public static String getStr(long sum) {
        if (sum >= 0 && 510_000 > sum) {
            return "#";
        } else if (sum >= 510_000 && sum < 1_020_000) {
            return "o";
        } else if (sum >= 1_020_000 && 1_530_000 > sum) {
            return "+";
        } else if (sum >= 1_530_000 && 2_040_000 > sum) {
            return "-";
        } else {
            return ".";
        }
    }

    public static long getRGB(int r, int g, int b) {
        return 2126 * r + 7152 * g + 722 * b;
    }
}
