import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    boolean[][][] visited;
    char[][] map;
    int[][] xy = {{0,1},{1,0},{0,-1},{-1,0}};
    List<Integer> answerList;
    int r;
    int c;


    public static void main(String[] args) {
        Solution so = new Solution();
        String[] grid = {"R","R"};
        for(int k : so.solution(grid)){
            System.out.println(k);
        }
    }

    public int[] solution(String[] grid) {
        int[] answer = {};
        answerList = new ArrayList<>();
        this.map = Arrays.stream(grid).map(a -> a.toCharArray()).toArray(char[][]::new);

        this.r = map.length;
        this.c = map[0].length;
        visited = new boolean[4][r][c];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if(visited[i][j][k] == false){
                        move(j,k,i);
                    }
                }
            }
        }
        return answerList.stream().sorted().mapToInt(Integer::valueOf).toArray();
    }

    private void move(int startR, int startY, int ro){
        int cycleLen = 0;
        int cr = -1;
        int cy = -1;
        int cro = -1;
        visited[ro][startR][startY] = true;
        while((cr == startR && cy == startY && cro == ro) == false){
            if(cycleLen == 0){
                cr = startR;
                cy = startY;
                cro = ro;
            }
            cycleLen++;
            int[] xy = nextXY(cro,cr,cy);
            cr = xy[0];
            cy = xy[1];
            cro = xy[2];
            if(visited[cro][cr][cy] == false){
                visited[cro][cr][cy] = true;
            }
            else{
                break;
            }

        }
        answerList.add(cycleLen);
    }


    private int[] nextXY(int cro, int cr, int cy){
        cr += xy[cro][0];
        cy += xy[cro][1];
        if(cr==map.length){
            cr = 0;
        }
        if(cr==-1){
            cr = map.length-1;
        }
        if(cy == map[0].length){
            cy = 0;
        }
        if(cy==-1){
            cy = map[0].length-1;
        }
        if(map[cr][cy] == 'S'){

        }
        else if(map[cr][cy] == 'L'){
            if(cro==0){
                cro =3;
            }
            else if(cro==1){
                cro = 0;
            }
            else if(cro==2){
                cro = 1;
            }
            else if(cro==3){
                cro = 2;
            }

        }
        else if(map[cr][cy] == 'R'){
            if(cro==0){
                cro = 1;
            }
            else if(cro==1){
                cro = 2;
            }
            else if(cro==2){
                cro = 3;
            }
            else if(cro==3){
                cro = 0;
            }

        }
        int[] ret = {cr,cy,cro};
        return ret;
    }
}
