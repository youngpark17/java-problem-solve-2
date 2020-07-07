import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x;
    static int[][] distance;
    static int INF = 1000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        distance = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    continue;
                }
                distance[i][j] = INF;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            distance[a][b] = Math.min(distance[a][b],c);
        }
        for (int i = 1; i < n + 1; i++) { //거쳐감
            for (int j = 1; j < n + 1; j++) { //출발정점
                if (distance[j][i] != INF) {
                    for (int k = 1; k < n + 1; k++) { //도착정점
                        distance[j][k] = Math.min(distance[j][k], distance[j][i]+distance[i][k]);
                    }
                }

            }
        }
        int answer = -1;
        for (int i = 1; i < n + 1; i++) {
            //학생 1이 x는. distance[1][x]+distance[x][1]
            answer = Math.max(distance[i][x] + distance[x][i], answer);

        }
        System.out.println(answer);

    }
}
