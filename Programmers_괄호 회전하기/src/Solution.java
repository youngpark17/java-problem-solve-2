import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("[](){}"));
    }

    public int solution(String s) {
        int answer = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            String tmpS = s.substring(i, len) + s.substring(0, i);
            if (isCorrect(tmpS)) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isCorrect(String tmpS) {
        char[] c = tmpS.toCharArray();
        Deque<Character> deque = new LinkedList();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '{' || c[i] == '[' || c[i] == '(') {
                deque.push(c[i]);
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                if (c[i] == '}') {
                    if (deque.peek() == '{') {
                        deque.pop();
                    }
                } else if (c[i] == ']') {
                    if (deque.peek() == '[') {
                        deque.pop();
                    }
                } else if (c[i] == ')') {
                    if (deque.peek() == '(') {
                        deque.pop();
                    }
                }
            }
        }

        if (deque.size() != 0) {
            return false;
        }
        return true;
    }

}
