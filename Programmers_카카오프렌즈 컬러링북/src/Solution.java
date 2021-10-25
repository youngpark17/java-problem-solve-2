import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    boolean[][] visited;
    int maxArea = -1;
    int maxSizeOfArea = -1;
    int m;
    int n;
    int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    static class Node{
        int r;
        int c;
        int n;
        Node(int r, int c, int n){
            this.r=r;
            this.c=c;
            this.n=n;
        }
    }
    public static void main(String[] args) {
        int[][] pictures = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int m = 6;
        int n = 4;
        Solution so = new Solution();
        int[] answer = so.solution(m,n,pictures);
        System.out.println(answer[0]);
        System.out.println(answer[1]);

    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        Queue<Node> que = new LinkedList<>();
        this.m=m;
        this.n=n;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == false && picture[i][j] != 0){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(bfs(i,j,picture),maxSizeOfOneArea);
                }

            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public int bfs(int r, int c, int[][] picture){
        int cnt = 0;
        visited[r][c] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(r,c,picture[r][c]));
        while(!que.isEmpty()){
            Node outNode = que.poll();
            cnt++;
            int a = outNode.r;
            int b = outNode.c;
            for (int i = 0; i < 4; i++) {
                int na = a+d[i][0];
                int nb = b+d[i][1];
                if(na>=0 && nb>=0 && na<this.m && nb<this.n){
                    if(visited[na][nb] == false && picture[na][nb] == outNode.n){
                        visited[na][nb] = true;
                        que.add(new Node(na,nb,outNode.n));
                    }
                }
            }
        }
        return cnt;
    }
}
