import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static boolean[] visited;
    static List<Node> chickenList;
    static List<Node> houseList;
    static int d = Integer.MAX_VALUE;
    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                if(a==2 ){
                    Node node = new Node(i,j);
                    chickenList.add(node);
                }
                else if(a == 1){
                    Node node = new Node(i,j);
                    houseList.add(node);
                }
            }
        }
        int chickenSize = chickenList.size();
        visited = new boolean[chickenSize];
        combination(chickenSize,0,0);
        System.out.println(d);

    }
    public static void combination(int p,int q,int r){
        if(m==r){
            setDistance();
            return;
        }
        else{
            for (int i = q; i < p; i++) {
                if(visited[i] == false){
                    visited[i] = true;
                    combination(p,i+1,r+1);
                    visited[i] = false;
                }
            }
        }
    }

   public static void setDistance(){
        int d = 0;
        int houseSize = houseList.size();
        int chickenSize = chickenList.size();
       for (int i = 0; i < houseSize; i++) {
           Node house = houseList.get(i);
           int hd = Integer.MAX_VALUE;
           for (int j = 0; j < chickenSize ; j++) {
                if(visited[j] == true){
                    Node chicken = chickenList.get(j);
                    hd = Math.min(hd,Math.abs(house.x - chicken.x) + Math.abs(house.y-chicken.y));
                }
           }
           d += hd;
       }
       Main.d = Math.min(Main.d,d);

   }
}
