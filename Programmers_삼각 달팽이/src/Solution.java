import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {

    boolean[][] visited;
    int k;
    int[][] d = {{1, 0}, {0, 1}, {-1, -1}};
    int[][] arr;

    public static void main(String[] args) {
        Solution so = new Solution();
        int[] answer = so.solution(4);
        for(int k : answer){
            System.out.print(k+" ");
        }
    }

    public int[] solution(int n) {
        int[] answer = {};
        arr = new int[n][];
        visited = new boolean[n][];
        k = (n) * (n + 1) / 2;
        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1];
            visited[i] = new boolean[i + 1];
        }
        dfs(0, 1, 0, 0);
        answer = Arrays.stream(arr).flatMapToInt(IntStream::of).toArray();
        return answer;
    }

    /*
    * 재귀로 짰다가 stackoverflow... 로인해 while 로 변경
    * */
    public void dfs(int nextRo, int count, int r, int c) {
        int nr=0;
        int nc=0;
        while(true){
            if(count != 1){
                r = nr;
                c = nc;
            }
            visited[r][c] = true;
            this.arr[r][c] = count;
            nr = r + d[nextRo][0];
            nc = c + d[nextRo][1];
            if (count == k) {
                return;
            }
            if (nextRo == 0) {
                if (canNotGo(nr,nc)) {
                    nextRo = 1;
                    nr = r;
                    nc = c;
                } else {
                    count+=1;
                }
            } else if (nextRo == 1) {
                if (canNotGo(nr,nc)) {
                    nextRo = 2;
                    nr = r;
                    nc = c;
                } else {
                    count+=1;
                }
            } else if (nextRo == 2) {
                if (canNotGo(nr,nc)) {
                    nextRo = 0;
                    nr = r;
                    nc = c;
                } else {
                    count+=1;
                }
            }
        }


    }

    boolean canNotGo(int nr, int nc){
        int l1 = visited.length;
        if(l1<=nr || nr<0){
            return true;
        }
        int l2 = visited[nr].length;
        if(l2<=nc || nc<0){
            return true;
        }
        if(visited[nr][nc] == true){
            return true;
        }

        return false;
    }
}
