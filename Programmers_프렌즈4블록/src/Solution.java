public class Solution {
    boolean[][] willRemoved;
    char[][] boards;
    public static void main(String[] args) {
        Solution so = new Solution();
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(so.solution(6,6,board));
    }
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        this.boards = new char[m][n];

        for (int i = 0; i < m; i++) {
            boards[i] = board[i].toCharArray();
        }
        String[] newBoard = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(boards[j][i]);
            }
            newBoard[i] = sb.toString();
        }
        boards = new char[n][m];
        willRemoved = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            boards[i] = newBoard[i].toCharArray();
        }
        int leftNum = m*n;
        while(true){
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < m-1; j++) {
                    removeCheck(i,j);
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(willRemoved[i][j] == true){
                        boards[i][j] = '1';
                        willRemoved[i][j] = false;
                        cnt++;
                    }
                }
            }

            if(cnt==0){
                break;
            }
            for (int i = 0; i < n; i++) {
                String k = new String(boards[i]);
                k = k.replaceAll("1","");
                k = "111111111111111111111111111111111111".substring(0,m-k.length()) + k;
                boards[i] = k.toCharArray();
            }
            leftNum -= cnt;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(boards[i][j] == '1'){
                    answer++;
                }
            }
        }

        return answer;
    }

    private void removeCheck(int r, int c){
        char c1 = boards[r][c];
        char c2 = boards[r+1][c];
        char c3 = boards[r][c+1];
        char c4 = boards[r+1][c+1];
        if(c1==c2&& c2==c3 && c3==c4){
            if(c1 == '1'){
                return;
            }
            willRemoved[r][c] = true;
            willRemoved[r+1][c] = true;
            willRemoved[r][c+1] = true;
            willRemoved[r+1][c+1] = true;
        }
    }
}
