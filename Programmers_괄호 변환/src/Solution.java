import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("()))((()"));
    }

    public String solution(String p) {

        return getAnswer(p);
    }

    String getAnswer(String p) {
        if (p.length() == 0) {
            return "";
        }
        if(isCorrect(p)){
            return p;
        }
        String[] divided = divideInput(p);
        if (isCorrect(divided[0])) {
            return divided[0] + getAnswer(divided[1]);
        } else {
            String empty = "(" + getAnswer(divided[1]) + ")";
            char[] tmp = divided[0].substring(1, divided[0].length() - 1).toCharArray();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == ')') {
                    tmp[i] = '(';
                } else {
                    tmp[i] = ')';
                }
            }
            empty += new String(tmp);
            return empty;

        }
    }

    boolean isCorrect(String u) {
        char[] us = u.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < us.length; i++) {
            if (deque.isEmpty()) {
                deque.add(us[i]);
            } else {
                if (deque.getLast() == '(' && us[i] == ')') {
                    deque.pollLast();
                } else {
                    deque.add(us[i]);
                }
            }
        }
        if (deque.size() > 0) {
            return false;
        }
        return true;
    }

    public String[] divideInput(String p) {
        int idx = getDividingIndex(p);
        String u = p.substring(0, idx);
        String v = p.substring(idx);
        String[] ret = {u, v};
        return ret;
    }

    public int getDividingIndex(String p) {
        char[] ps = p.toCharArray();
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < ps.length; i++) {
            if (ps[i] == '(') {
                cnt1++;
            } else {
                cnt2++;
            }
            if ((cnt1 != 0 || cnt2 != 0) && cnt1 == cnt2) {
                return i + 1;
            }
        }
        return -1;
    }
}
