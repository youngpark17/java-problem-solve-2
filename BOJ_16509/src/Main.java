import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int r1, c1, r2, c2;
    static int[][] map;
    static boolean[][] visited;
    static int[][][] routes = {{{0, -1}, {-1, -1}, {-1, -1}},
            {{0, -1}, {1, -1}, {1, -1}},
            {{-1, 0}, {-1, -1}, {-1, -1}},
            {{-1, 0}, {-1, 1}, {-1, 1}},
            {{0, 1}, {-1, 1}, {-1, 1}},
            {{0, 1}, {1, 1}, {1, 1}},
            {{1, 0}, {1, 1}, {1, 1}},
            {{1, 0}, {1, -1}, {1, -1}}
    };

    static int answer = -1;

    static class Node {
        int x;
        int y;
        int num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        r1 = Integer.parseInt(tmp[0]);
        c1 = Integer.parseInt(tmp[1]);
        tmp = br.readLine().split(" ");
        r2 = Integer.parseInt(tmp[0]);
        c2 = Integer.parseInt(tmp[1]);
        map = new int[10][9];
        visited = new boolean[10][9];
        findKing(r1, c1);
        System.out.println(answer);
    }

    public static void findKing(int sr, int sc) {
        Node node = new Node(sr, sc, 0);
        visited[sr][sc] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(node);
        while (que.isEmpty() == false) {
            Node out = que.poll();
            int cr = out.x;
            int cc = out.y;
            if (cr == r2 && cc == c2) {
                answer = out.num;
            }
            for (int[][] route : routes) {
                int nx = cr;
                int ny = cc;
                for (int i = 0; i < 3; i++) {
                    int[] rout = route[i];
                    nx += rout[0];
                    ny += rout[1];
                    if (canGo(nx, ny, i) == true) {
                        if (i == 2) {
                            Node in = new Node(nx, ny, out.num + 1);
                            visited[nx][ny] = true;
                            que.add(in);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static boolean canGo(int nx, int ny, int idx) {
        if (nx < 0 || ny < 0 || nx >= 10 || ny >= 9) {
            return false;
        }
        if (visited[nx][ny] && idx == 2) {
            return false;
        }
        if (idx != 2 && nx == r2 && ny == c2) {
            return false;
        }
        return true;
    }
}
