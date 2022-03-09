import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(new String[]{"I 7","I 5","I -5","D -1"}));
    }
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq1 = new PriorityQueue();
        PriorityQueue<Integer> pq2 = new PriorityQueue(Collections.reverseOrder()); //최대
        for(String op : operations){
            String[] o = op.split(" ");
            int num = Integer.parseInt(o[1]);
            if("I".equals(o[0])){
                pq1.add(num);
                pq2.add(num);
            }
            else if("D".equals(o[0])){
                if(pq1.size()>0){
                    if(num==1){
                        int max = pq2.peek();
                        pq1.remove(max);
                        pq2.remove(max);
                    }
                    else{
                        int min = pq1.peek();
                        pq1.remove(min);
                        pq2.remove(min);
                    }
                }
            }
        }
        if(pq1.size() == 0){
            return new int[]{0,0};
        }

        return new int[]{pq2.peek(),pq1.peek()};
    }

}
