import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    class Node{
        int x;
        int p;
        Node(int x, int p){
            this.x=x;
            this.p=p;
        }
    }
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] pri = {2,1,3,2};
        int lo = 2;
        System.out.println(so.solution(pri,lo));
    }
    public int solution(int[] priorities, int location) {
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            deque.add(new Node(i,priorities[i]));
        }
        int answer = 0;
        while(true){
            Node n = deque.pollFirst();
            int p = n.p;
            if(deque.size() == 0){
                return answer+1;
            }
            int maxP = deque.stream().max((a,b)->a.p-b.p).get().p;
            if(p<maxP){
                deque.addLast(n);
            }
            else{
                answer++;
                if(n.x == location){
                    return answer;
                }

            }
        }
    }
}
