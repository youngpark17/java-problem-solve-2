import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    boolean[][] visited;
    char[][] ps;
    int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int[] solution(String[][] places) {
        int[] answer = {};
        visited = new boolean[5][5];
        List<Integer> list = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            char[][] ps = new char[5][5];
            for (int b = 0; b < 5; b++) {
                ps[b] = places[a][b].toCharArray();
            }
            this.ps = ps;
            loop:
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Arrays.fill(visited[j],false);
                }
                for (int j = 0; j < 5; j++) {
                    if (ps[i][j] == 'P') {
                        visited[i][j] = true;
                        if (dfs(i, j, 0) == false) {
                            list.add(0);
                            break loop;
                        }
                    }
                }
                if(i==4){
                    list.add(1);
                }
            }
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public boolean dfs(int r, int c, int depth) {
        if (ps[r][c] == 'P' && depth !=0) {
            return false;
        }
        if (depth < 2) {
            for (int i = 0; i < 4; i++) {
                int nr = r + d[i][0];
                int nc = c + d[i][1];
                if (canGo(nr, nc)) {
                    visited[nr][nc] = true;
                    if (dfs(nr, nc, depth + 1) == false) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean canGo(int r, int c) {
        if (r >= 5 || c >= 5 || r < 0 || c < 0) {
            return false;
        }
        if (visited[r][c] == true) {
            return false;
        }
        if (ps[r][c] == 'X') {
            return false;
        }
        return true;
    }

}
