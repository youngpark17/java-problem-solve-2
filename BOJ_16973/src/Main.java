import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int h, w, sr, sc, fr, fc;
    static boolean[][] visited;
    static int[][] cumSum;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int ans;

    static class Node {
        int r;
        int c;
        int cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        cumSum = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    cumSum[i][j] = map[i][j];
                } else {
                    cumSum[i][j] = map[i][j] + cumSum[i][j - 1];
                }

            }
        }
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;
        ans = -1;
        bfs();
        System.out.println(ans);
    }

    public static void bfs() {
        Node node = new Node(sr, sc, 0);
        Queue<Node> que = new LinkedList<>();
        visited[sr][sc] = true;
        que.add(node);
        while (que.isEmpty() == false) {
            Node out = que.poll();
            if (out.r == fr && out.c == fc) {
                ans = out.cost;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = out.r + dx[i];
                int nc = out.c + dy[i];
                if (isRange(nr, nc)) {
                    visited[nr][nc] = true;
                    que.add(new Node(nr, nc, out.cost + 1));
                }
            }
        }
    }

    public static boolean isRange(int r, int c) {
        int r2 = r;
        int c2 = c + w - 1;
        int r3 = r + h - 1;
        int c3 = c + w - 1;
        int r4 = r + h - 1;
        int c4 = c;
        int[] ar = {r, r3};
        int[] ac = {c, c3};
        boolean flag1 = Arrays.stream(ar).allMatch(R -> R >= 0 && R < n);
        boolean flag2 = Arrays.stream(ac).allMatch(C -> C >= 0 && C < m);
        //구간합을 미리 저장해놓고 그 구간에 1이 포함되어있나?
        boolean flag3 = false;
        int sum = 0;
        if (flag1 && flag2) {
            if (visited[r][c] == true) {
                return false;
            }
            for (int i = r; i < r + h; i++) {
                if (c - 1 >= 0) {
                    sum += cumSum[i][c2] - cumSum[i][c - 1];
                } else {
                    sum += cumSum[i][c2];
                }

            }
            if (sum == 0) {
                flag3 = true;
            }
        }
        return flag1 && flag2 && flag3;

    }
}
