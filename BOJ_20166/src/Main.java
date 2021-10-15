import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static char[][] charWorlds;
    static int answer;
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    static Map<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        charWorlds = new char[n][m];
        answer = 0;
        for (int i = 0; i < n; i++) {
            charWorlds[i] = br.readLine().toCharArray();
        }
        map = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String str = br.readLine();
            list.add(str);
            map.put(str, 0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(charWorlds[i][j]);
                dfs(i, j, sb, 1);
                sb.deleteCharAt(sb.toString().length() - 1);
            }
        }

        list.stream().forEach((str) -> {
            System.out.println(map.get(str));
        });
    }

    public static void dfs(int r, int c, StringBuilder sb, int depth) {
        String str = sb.toString();
        if (depth > 5) {
            return;
        }
        if (map.containsKey(str)) {
            map.put(str, map.get(str) + 1);
        }
        for (int[] dd : d) {
            int nr = r + dd[0];
            int nc = c + dd[1];
            int[] tmp = canMove(nr, nc);
            nr = tmp[0];
            nc = tmp[1];
            sb.append(charWorlds[nr][nc]);
            dfs(nr, nc, sb, depth + 1);

            sb.deleteCharAt(sb.length() - 1);

        }
    }


    public static int[] canMove(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= m) {
            if (r == -1) {
                r = n - 1;
            }
            if (r == n) {
                r = 0;
            }
            if (c == -1) {
                c = m - 1;
            }
            if (c == m) {
                c = 0;
            }
        }
        int[] arr = {r, c};
        return arr;
    }

}
