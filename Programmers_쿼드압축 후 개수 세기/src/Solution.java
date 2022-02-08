import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
    List<Integer> list;
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] arr = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}};
        for(int k : so.solution(arr)){
            System.out.println(k);
        }
    }

    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        dfs(arr,0,0,arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j]==0){
                    answer[0]++;
                }
                else if(arr[i][j] == 1){
                    answer[1]++;
                }
            }
        }
        return answer;
    }

    private void dfs(int[][] arr,int r, int c,int len){
         if(len <2){
            return;
        }
        int k = isAllSame(arr,r,c,len);
        if(k==0 || k==1){
            check(arr,r,c,len);
            return;
        }
        dfs(arr,r,c,len/2);
        dfs(arr,r,c+len/2,len/2);
        dfs(arr,r+len/2,c,len/2);
        dfs(arr,r+len/2,c+len/2,len/2);
    }

    private void check(int[][] arr, int r, int c, int len){
        for (int i = r; i < r+len; i++) {
            for (int j = c; j < c+len; j++) {
                if(i == r && j ==c) continue;
                arr[i][j] = -1;
            }
        }
    }

    private int isAllSame(int[][] arr,int r, int c, int len){
        int sum = 0;
        for (int i = r; i < r+len; i++) {
            for (int j = c; j < c+len; j++) {
                sum+=arr[i][j];
            }
        }
        int size = len*len;
        if(sum == 0){
            return 0;
        }
        if(sum%size == 0 && sum/size ==1){
            return 1;
        }
        return 2;
    }

}
