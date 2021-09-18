import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, d, k, c;
    static int[] sushi;
    static Set<Integer> sushiSet;
    static boolean flag;
    static boolean flag2;
    static int couponListCount;
    static int ans = -1;
    static int[] sushiCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[N];
        flag = false;
        flag2 = false;
        sushiSet = new HashSet<>();
        couponListCount = 0;
        sushiCount = new int[d + 1];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            if (sushi[i] == c) {
                flag2 = true;
            }
        }
        for (int i = 0; i < N+k; i++) {
             getEatNumber(i);

        }
        System.out.println(ans);


    }

    public static void getEatNumber(int s) {
        int idx = s % N;
        int prevIdx = (s - k);
        if(prevIdx>=0){
            int removeSushi = sushi[prevIdx];
            sushiCount[removeSushi] -= 1;
            if (sushiCount[removeSushi] == 0) {
                sushiSet.remove(removeSushi);
            }
        }
        int addSushi = sushi[idx];
        sushiSet.add(addSushi);
        sushiCount[addSushi] += 1;
        if (s >= k-1) {
            if (sushiCount[c] == 0) {
                ans = Math.max(ans, sushiSet.size() + 1);
            } else {
                ans = Math.max(ans, sushiSet.size());
            }
        }

    }
}
