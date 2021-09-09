import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    boolean[] visited;
    int max = 0;
    int[] priceG;
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] price = {0,1,2};
        System.out.println(so.solution(3,price));

    }

    public int solution(int n, int[] price) {
        int answer = -1;
        priceG = price;
        visited = new boolean[n];
        dfs(0,n,0,0);

        return max;
    }
    // -> 동적계획법으로 바꾸서 풀 수 있을듯.
    // -> 돈이 있다면..? dp[인덱스][돈][수량]
    private void dfs(int start, int last, int total, int value){
        if(start==last){
            max = Math.max(max,value);
            return ;
        }
        for (int i = start; i < last ; i++) {
            if(!visited[i]){
                if(priceG[i] == 0){
                    visited[i] = true;
                    dfs(i+1,last,total+1,value);
                }
                else{
                    if(value>=priceG[i]){ //살돈이 있다면
                        dfs(i+1,last,total+1,value-priceG[i]);
                    }
                    dfs(i+1,last,total,value); //아무것도 안할거
                    if(total>0){
                        dfs(i,last,total-1,value+priceG[i]);
                    }
                }
            }

            }
        }


}
