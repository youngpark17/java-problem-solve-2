import java.util.*;

public class Solution {
    PriorityQueue<Node> pq;
    int[] costs;

    static class Node {
        int num;
        int len;
        Node(int num, int len) {
            this.num = num;
            this.len = len;
        }

        public int getLen() {
            return len;
        }
    }

    public static void main(String[] args) {
        int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int N = 6;
        int K = 4;
        Solution so = new Solution();
        System.out.println(so.solution(N, road, K));
    }

    public int solution(int N, int[][] road, int K) {
        pq = new PriorityQueue<>(Comparator.comparing(Node::getLen));
        List<Node>[] adjList = new List[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<Node>();
        }
        for(int[] r : road){
            r[1]-=1;
            r[0]-=1;
            Node in = new Node(r[1], r[2]);
            adjList[r[0]].add(in);
            adjList[r[1]].add(new Node(r[0],r[2]));
        }
        costs = new int[N];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        pq.add(new Node(0,0));
        while(!pq.isEmpty()){
            Node current = pq.poll();
            int start = current.num;
            for(Node in : adjList[start]){
                if(costs[in.num] >=  in.len+costs[start] && in.len+costs[start] <= K){
                    costs[in.num] = in.len+costs[start];
                    pq.add(new Node(in.num, in.len+costs[start]));
                }
            }
        }

        int answer = (int)Arrays.stream(costs).filter(a -> a != Integer.MAX_VALUE).count();
        return  answer;
    }
}
