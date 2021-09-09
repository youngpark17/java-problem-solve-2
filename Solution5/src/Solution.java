import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] prices = {5, 3, 7, 9, 5, 2, 4, 9, 10, 6};
        int[] ans = s.solution(prices);
        for (int k : ans) {
            System.out.print(k + " ");
        }
        //5 7 3 1 3 4 3 1 0 0
    }
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            int cnt = 0;

            int value = prices[i];
            answer[i] = (int)Arrays.stream(Arrays.copyOfRange(prices,i+1,len))
                        .filter(a -> value < a).count();
        }

        return answer;
    }

//    public int[] solution(int[] prices) {
//        int len = prices.length;
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        int[] answer = new int[len];
//        for (int i = len - 1; i >= 0; i--) {
//            int value = prices[i];
//
//            SortedSet<Integer> ss = treeSet.tailSet(value,false);
//            answer[i] = ss.size();
//            treeSet.add(value);
//        }
//        return answer;
//    }


}

class Node {
    int idx;
    int price;

    Node(int idx, int price) {
        this.idx = idx;
        this.price = price;
    }
}

