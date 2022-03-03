import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    char[][] map;
    TreeMap<String,Node> tmap;
    int[][] d = {{-1,0},{1,0},{0,1},{0,-1}};
    boolean[][] visited;
    int n;
    static class Node{
        char c;
        int x;
        int y;
        Node(char c, int x, int y){
            this.c=c;
            this.x=x;
            this.y=y;
        }

        public char getC(){
            return c;
        }
    }
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(3,3, new String[]{"AAB","...","BCC"}));
    }

    public String solution(int m, int n, String[] board) {
        String answer = "";
        List<Character> list = new ArrayList<>();
        n = board.length;
        map = new char[board.length][board[0].length()];
        tmap = new TreeMap<String,Node>();
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < board[0].length(); j++) {
                if(map[i][j]>='A' && map[i][j] <= 'Z'){
                    tmap.put(map[i][j]+""+cnt++,new Node(map[i][j],i,j));
                }
            }
        }
        int tt = tmap.size()/2;
        int prevSize = -1;
        while(tmap.size() != prevSize){
            prevSize = tmap.size();
            List<String> tmpList = tmap.keySet().stream().collect(Collectors.toList());
            //k1 -> k2 찾기
            for (int i = 0; i < tmpList.size(); i+=2) {
                String k1 = tmpList.get(i);
                String k2 = tmpList.get(i+1);
                visited = new boolean[map.length][map[0].length];
                if(dfs(tmap.get(k1).x,tmap.get(k1).y,tmap.get(k2).x,tmap.get(k2).y,-5)){
                    map[tmap.get(k1).x][tmap.get(k1).y] = '.';
                    map[tmap.get(k2).x][tmap.get(k2).y] = '.';
                    list.add(tmap.get(k1).c);
                    tmap.remove(k1);
                    tmap.remove(k2);
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(sb::append);
        return sb.toString().length() < tt ? "IMPOSSIBLE": sb.toString();
    }

    private boolean dfs(int x, int y, int r, int c, int t){
        visited[x][y] = true;
        if(x==r && y==c){
            return true;
        }
        else{
            //첫 방향
            if(t==-5){
                for (int i = 1; i < 5; i++) {
                    int[] dd = d[i-1];
                    int nx = x+dd[0];
                    int ny = y+dd[1];
                    if(canGo(nx,ny, map[r][c]) &&dfs(nx,ny,r,c,i*-1)){
                        return true;
                    }
                }
            }
             else if(t<0){
                 int k = (t*-1)-1;
                 int tx = x+d[k][0];
                 int ty = y+d[k][1];
                 if(canGo(tx,ty,map[r][c]) && dfs(tx,ty,r,c,t)){
                     return true;
                 }
                 for (int i = 0; i < 4; i++) {
                    int[] dd = d[i];
                    int nx = x+dd[0];
                    int ny = y+dd[1];
                    if(canGo(nx,ny, map[r][c]) &&dfs(nx,ny,r,c,i)){
                        return true;
                    }
                }
            }
            else{
                int nx = x+d[t][0];
                int ny = y+d[t][1];
                if(canGo(nx,ny,map[r][c]) && dfs(nx,ny,r,c,t)){
                    return true;
                }
            }

        }
        visited[x][y] = false;
        return false;
    }

    private boolean canGo(int nx, int ny, char c){
        if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length){
            return false;
        }
        if(visited[nx][ny]){
            return false;
        }
        //길이 아니라면
        if(map[nx][ny] != '.' && map[nx][ny] != c){
            return false;
        }
        return true;
    }
}
