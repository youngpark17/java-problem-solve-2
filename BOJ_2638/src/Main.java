import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) {
                    total += 1;
                }
                map[i][j] = k;
            }
        }
        int timer = 0;
        while (total > 0) {
            timer++;
            bfs(0, 0);
            melt();
            init();
        }
        System.out.println(timer);
    }

    public static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void melt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    int vtxCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isInLine(nx, ny)) {
                            if (visited[nx][ny] && map[nx][ny] == 0) {
                                vtxCnt += 1;
                            }
                        }
                    }
                    if (vtxCnt >= 2) {
                        map[i][j] = 0;
                        total -= 1;
                    }
                }
            }
        }
    }

    public static void bfs(int startX, int startY) {
        Queue<Node2> que = new LinkedList<>();
        visited[startX][startY] = true;
        que.add(new Node2(startX, startY));
        while (!que.isEmpty()) {
            Node2 node = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (isInLine(nx, ny)) {
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        que.add(new Node2(nx, ny));
                    }
                }

            }
        }
    }

    public static boolean isInLine(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return false;
        }
        return true;
    }

}

class Node2 {
    int x;
    int y;

    Node2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
