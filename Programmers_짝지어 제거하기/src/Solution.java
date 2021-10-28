import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("baabaa"));
    }

    public int solution(String s) {
        int answer = -1;
        Deque<Character> deque = new LinkedList<>();
        char[] tmp = s.toCharArray();
        for(char c : tmp){
            if(deque.isEmpty()){
                deque.add(c);
            }
            else{
                if(deque.peekLast() == c){
                    deque.removeLast();
                }
                else{
                    deque.add(c);
                }
            }
        }
        if(deque.size()==0){
            return 1;
        }

        return 0;
    }
}
