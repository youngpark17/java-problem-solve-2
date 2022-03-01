import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    int[][] relations;

    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(so.solution(5, results));
    }

    public int solution(int n, int[][] results) {
        int answer = 0;
        relations = new int[n][n];
        for(int[] re : results){
            int a1 = re[0]-1;
            int a2 = re[1]-1;
            relations[a1][a2] = 1;
            relations[a2][a1] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(relations[i][j] == 1 && relations[j][k] == 1){
                        relations[i][k] = 1;
                        relations[k][i] = -1;
                    }
                    else if(relations[i][j] == -1 && relations[j][k] == -1){
                        relations[i][k] = -1;
                        relations[k][i] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int cnt = (int)Arrays.stream(relations[i]).filter(r -> r == 1 || r == -1).count();
            if(cnt == n-1){
                answer++;
            }
        }

        return answer;
    }


}
