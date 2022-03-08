import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<Integer>[] tree;
    static long[] aa;
    static long answer = 0;
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(new int[]{-5,0,2,1,2}, new int[][]{{0,1},{3,4},{2,3},{0,3}}));
    }

    public long solution(int[] a, int[][] edges) {
        long[] aa = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            aa[i] = a[i];
        }
        tree = new List[a.length];
        this.aa = aa;
        if(Arrays.stream(a).sum() != 0){
            return -1;
        }
        for (int i = 0; i < a.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
         dfs(0,-1);
         if(aa[0] != 0){
             return -1;
         }
        return answer;
    }

    private void dfs(int c, int parent){
        for(int next :tree[c]){
            if(next != parent){
                dfs(next, c);
            }
        }
        if(parent == -1){
            return;
        }
        if(aa[c] < 0){
            aa[parent] -= Math.abs(aa[c]);
            answer+=Math.abs(aa[c]);
            aa[c] = 0;
        }
        else{
            aa[parent] += aa[c];
            answer+=aa[c];
            aa[c]=0;
        }
    }
}
