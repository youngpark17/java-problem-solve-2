
public class Solution {
    boolean[] visited;
    int n;
    int[][] dun;
    int ans;
    int[][] pick;
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        int k = 80;
        System.out.println(so.solution(k,dungeons));
    }
    public int solution(int k, int[][] dungeons) {
        this.dun = dungeons;
        n = this.dun.length;
        visited = new boolean[n];
        pick = new int[n][2];
        ans =  0;
        dfs(n,0,k);
        return ans;
    }

    private void dfs(int N,int r,int k){
        if(r==N){
            int cnt = 0;
            for(int[] p : pick){
                if(canGo(p[0],p[1],k)){
                    cnt++;
                    k-=p[1];
                }
            }
            ans = Math.max(cnt,ans);
        }
        else{
            for (int i = 0; i < N; i++) {
                if(visited[i] == false){
                    visited[i] = true;
                    pick[r] = dun[i];
                    dfs(N,r+1, k);
                    visited[i] = false;
                }
            }
        }
    }

    private boolean canGo(int a, int b, int k){
        if(k>=a && k>=b){
            return true;
        }
        return false;
    }



}
