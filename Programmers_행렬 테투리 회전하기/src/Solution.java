import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    int[][] arrs;
    int tmp;
    int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Solution so  = new Solution();
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        for(int k: so.solution(rows,columns,queries)){
            System.out.println(k);
        }
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        arrs  = new int[rows][columns];
        int cnt =1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arrs[i][j] = cnt++;
            }
        }
        //[1,1,4,3]
        //(1,1)->(1,2), (1,2)->(1,3),
        //재귀로 짜자.
        List<Integer> list = new ArrayList<>();
        Arrays.stream(queries).forEach(query ->{
            rotate(query[0]-1, query[1]-1,query[2]-1, query[3]-1);
            list.add(min);
            min = Integer.MAX_VALUE;
        });
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
    public void rotate(int sr, int sc, int er, int ec){
        int copySr = sr;
        int copySc = sc;
        int copyEr = er;
        int copyEc = ec;
        tmp = arrs[sr][sc];
        while(copySr!=er){
            arrs[copySr][sc] = arrs[copySr+1][sc];
            min = Math.min(arrs[copySr][sc],min);
            copySr+=1;
        }
        while(copySc!=ec){
            arrs[er][copySc] = arrs[er][copySc+1];
            min = Math.min(arrs[er][copySc],min);
            copySc+=1;
        }
        while(copyEr!=sr){
            arrs[copyEr][ec] = arrs[copyEr-1][ec];
            min = Math.min(arrs[copyEr][ec],min);
            copyEr-=1;
        };
        while(copyEc!=sc){
            arrs[sr][copyEc] = arrs[sr][copyEc-1];
            min = Math.min(arrs[sr][copyEc],min);
            copyEc-=1;
        }
        arrs[sr][sc+1] = tmp;
        min = Math.min(min,tmp);

    }
}
