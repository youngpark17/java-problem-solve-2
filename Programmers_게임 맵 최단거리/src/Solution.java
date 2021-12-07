import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    boolean[][] visited;
    int[][] d = {{0,1},{1,0},{-1,0},{0,-1}};
    class Node{
        int x;
        int y;
        int c;
        Node(int x, int y, int c){
            this.x=x;
            this.y=y;
            this.c=c;
        }
    }
    public static void main(String[] args) {
     Solution so = new Solution();
     int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
     System.out.println(so.solution(maps));
    }
    public int solution(int[][] maps) {
        int answer = 0;
        int r = maps.length;
        int c = maps[0].length;
        visited = new boolean[r][c];
        answer = bfs(maps,r,c);

        return answer;
    }

    private int bfs(int[][] maps, int r, int c){
        Node startNode = new Node(0,0,1);
        visited[0][0] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(startNode);
        while(que.isEmpty() == false){
            Node outNode = que.poll();
            int cr = outNode.x;
            int cy = outNode.y;
            for (int i = 0; i < 4; i++) {
                int nr = cr+d[i][0];
                int nc = cy+d[i][1];
                if(nr>=0 && nc>=0 && nr<r && nc<c){
                    if(maps[nr][nc] == 1 && visited[nr][nc] == false){
                        if(nr == r-1 && nc == c-1){
                            return outNode.c+1;
                        }
                        visited[nr][nc] = true;
                        que.add(new Node(nr,nc,outNode.c+1));
                    }
                }
            }
        }
        return -1;
    }

}
