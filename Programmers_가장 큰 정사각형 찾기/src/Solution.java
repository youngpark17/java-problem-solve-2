import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] board = new int[][]{
                {0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}
        };
        System.out.println(so.solution(board));
    }
    public int solution(int[][] board) {
        int answer = -1;
        int count = Arrays.stream(board).flatMapToInt(Arrays::stream).sum();
        if(count==0){
            return 0;
        }
        int l1 = board.length;
        int l2 = board[0].length;
        int[][] answerArr = new int[l1][l2];
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                if(board[i][j] == 1){
                    board[i][j] = 1 + Math.min(board[i][j-1], Math.min(board[i-1][j], board[i-1][j-1]));
                    answer = Math.max(answer,board[i][j]);
                }
            }
        }
        return answer*answer;
    }
}
