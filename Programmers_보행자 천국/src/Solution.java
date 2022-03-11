public class Solution {
    int MOD = 20170805;
    int[][] routes;
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(3,6,new int[][]{
                {0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}
        }));
    }

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        routes = new int[m][n];
        routes[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if(cityMap[0][i] != 1){
                routes[0][i] = routes[0][i-1];
            }
        }
        for (int i = 1; i < m; i++) {
            if(cityMap[i][0] != 1){
                routes[i][0] = routes[i-1][0];
            }
        }
        //2가 연속해서 있다면 2가 아닌거까지 구해서....
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <n; j++) {
                if(cityMap[i][j] == 1){
                    routes[i][j] = 0;
                    continue;
                }
                int r= 0;
                int c = 0;
                if(cityMap[i-1][j] == 2){
                    int idx = -1;
                    for (int k = i-2; k>=0 ; k--) {
                        if(cityMap[k][j]!=2){
                            idx = k;
                            break;
                        }
                    }
                    if(idx!=-1){
                        r = routes[idx][j];
                    }


                }
                else{
                    r = routes[i-1][j];
                }

                if(cityMap[i][j-1] == 2){
                    int idx = -1;
                    for (int k = j-2; k>=0 ; k--) {
                        if(cityMap[i][k]!=2){
                            idx = k;
                            break;
                        }
                    }
                    if(idx!=-1){
                        c = routes[i][idx];
                    }

                }
                else{
                    c = routes[i][j-1];
                }
                routes[i][j] = (r+c)%MOD;
            }
        }
        return routes[m-1][n-1];
    }
}
