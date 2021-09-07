import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int minCost;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                int cost = 0;
                if (isInLine(i, j)) {
                    checkMap(i, j, true);
                    int costMemo1 = getCost(i, j);
                    cost += costMemo1;
                    for (int k = 1; k < n-1; k++) {
                        for (int l = 1; l < n-1; l++) {
                            if (isInLine(k, l)) {
                                checkMap(k, l, true);
                                int costMemo2 = getCost(k, l);
                                cost += costMemo2;
                                if(cost>=minCost){
                                    cost -= costMemo2;
                                    checkMap(k, l, false);
                                    continue;
                                }
                                for (int m = 1; m < n-1; m++) {
                                    for (int o = 1; o < n-1; o++) {
                                        if (isInLine(m, o)) {
                                            int costMemo3 = getCost(m, o);
                                            cost += costMemo3;
                                            checkMap(m,o,true);
                                            minCost = Math.min(cost, minCost);
                                            cost -= costMemo3;
                                            checkMap(m,o,false);
                                        }
                                    }
                                }
                                cost -= costMemo2;
                                checkMap(k, l, false);
                            }
                        }
                    }
                    checkMap(i, j, false);
                }
            }
        }
        System.out.println(minCost);
    }

    public static void checkMap(int cx, int cy, boolean flag) {
        visited[cx][cy] = flag;
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            visited[nx][ny] = flag;
        }
    }

    public static int getCost(int cx, int cy) {
        int cost = map[cx][cy];
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            cost += map[nx][ny];
        }
        return cost;
    }

    public static boolean isInLine(int cx, int cy) {
        if(visited[cx][cy] == true){
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                return false;
            }
            if (visited[nx][ny] == true) {
                return false;
            }
        }
        return true;
    }
}

