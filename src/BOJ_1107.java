import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1107 {

    static int n;
    static boolean[] visited;
    static List<Integer> numbers;
    static int min;
    static int min2;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[10];
        numbers = new ArrayList<>();
        //0~9까지
        //n을 만들기 위해 뽑자. 중복조합.
        int k = Integer.parseInt(br.readLine());
        min = Math.abs(n - 100); //100에서 시작.
        min2 = 0;
        ans = min+min2;
        if (k == 0) {
            ans  = Math.min((n+"").length(),Math.abs(n-100));
            System.out.println(ans);
            System.exit(0);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            visited[Integer.parseInt(st.nextToken())] = true;
        }
        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                numbers.add(i);
            }
        }

        //numbers에서 중복으로 뽑자.
        for(int number: numbers){
            dfs(1, (n+"").length() + 1, number);
        }
        System.out.println(ans);

    }

    public static void dfs(int depth, int to, int number) {
        if (depth == to) {
            if(min>=Math.abs(number-n)){
                min = Math.abs(number-n);
                ans = Math.min(min+depth,ans);
            }
        } else {
            if(min>=Math.abs(number-n)){
                min = Math.abs(number-n);
                ans = Math.min(min+depth,ans);
            }
            for (int k : numbers) {
                dfs(depth + 1, to, number * 10 + k);
            }
        }
    }
}
