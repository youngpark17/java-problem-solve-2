import java.util.HashSet;
import java.util.Set;

public class Solution {
    int[][] d = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; //U D R L
    boolean[][][] visited;

    Set<Integer> set;
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("LRLR"));
    }
    public int solution(String dirs) {
        int answer = 0;
        char[] arr = dirs.toCharArray();
        int a = 5;
        int b = 5;
        visited = new boolean[11][11][4];
        for (char c : arr) {
            int fa = a;
            int fb = b;
            int sa = a;
            int sb = b;
            int k = 0;
            if (c == 'U') {
                fa += d[0][0];
                fb += d[0][1];
                k = 0;
            } else if (c == 'D') {
                fa += d[1][0];
                fb += d[1][1];
                k = 1;
            } else if (c == 'R') {
                fa += d[2][0];
                fb += d[2][1];
                k = 2;
            } else {
                fa += d[3][0];
                fb += d[3][1];
                k = 3;
            }
            if (isIn(fa, fb)) {
                a = fa;
                b = fb;
                visited[sa][sb][k] = true;
            }
        }
        set = new HashSet<>();
        //U, R로 통일
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 4; k++) {
                    if(visited[i][j][k]){
                        int t = i;
                        int t2 = j;
                        int t3 = k;
                        if(t3==1){ //D->U
                            t = i+1;
                            t2 = j;
                            t3 = 0;
                        }
                        else if(t3==3){ //L->R
                            t = i;
                            t2 = j-1;
                            t3 = 2;
                        }
                        int[] tmp = new int[3];
                        tmp[0] = t;
                        tmp[1] = t2;
                        tmp[2] = t3;
                        set.add(tmp[0]*10000+tmp[1]*100+tmp[2]);
                    }
                }
            }
        }
        return set.size();
    }

    private boolean isIn(int a, int b) {
        if (a > 10 || b > 10 || a < 0 || b < 0) {
            return false;
        }
        return true;
    }
}
