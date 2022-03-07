import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    boolean[] removed;
    int current;
    Deque<Integer> deque = new LinkedList<>();
    int n;

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }


    public String solution(int n, int k, String[] cmd) {
        Deque<Integer> order = new LinkedList<>();
        int size = n;
        for (int i = 0; i < cmd.length; i++) {
            char c = cmd[i].charAt(0);
            if (c == 'D')
                k += Integer.parseInt(cmd[i].substring(2));
            else if (c == 'U')
                k -= Integer.parseInt(cmd[i].substring(2));
            else if (c == 'C') {
                order.addLast(k);
                size--;
                if (k == size)
                    k--;
            } else if (c == 'Z') {
                if (order.pollLast() <= k)
                    k++;
                size++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++)
            builder.append("O");
        while (!order.isEmpty())
            builder.insert(order.pollLast().intValue(), "X");
        String answer = builder.toString();
        return answer;
    }

}

