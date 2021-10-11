import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static boolean[] visited;
    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
//            9
//            5 5
//            4 6
//            4 12
//            3 8
//            4 18
//            2 10
//            2 5
//            1 7
//            1 14
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        int n2 = n;
        List<Node> list = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.y-b.y);

        while(n-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node node = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            list.add(node);
        }
        Collections.sort(list,(a,b) -> a.x-b.x);
        int cnt = 0;
        for (int i = 0; i < n2; i++) {
            Node node = list.get(i);
            //새로 맞이하는 노드가 이전에 선택된것보다 더 크다면.
            if(pq.isEmpty() == false){
                Node outNode= pq.peek();
                if(outNode.y<node.y && cnt>=node.x){
                    pq.poll();
                    pq.add(node);
                }
                else if(cnt<node.x){
                    pq.add(node);
                    cnt++;
                }
            }
            else{
                pq.add(node);
                cnt++;
            }
        }
        int ans = 0;
        ans = pq.stream().mapToInt((node)->node.y).sum();
        System.out.println(ans);
    }
}
