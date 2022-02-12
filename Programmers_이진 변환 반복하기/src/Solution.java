import java.util.Arrays;
import java.util.StringJoiner;

public class Solution {
    String s;
    public static void main(String[] args) {
        Solution so = new Solution();
        Arrays.stream(so.solution(new String("110010101001"))).forEach(System.out::println);
    }

    public int[] solution(String k) {
        int[] answer = new int[2];
        this.s = k;
        while (!"1".equals(s)) {
            answer[1] += countZero();
            answer[0] += 1;
            this.s = Integer.toBinaryString(this.s.length());

        }
        return answer;
    }

    private int countZero() {
        StringJoiner sj = new StringJoiner("");
        Arrays.stream(s.split("")).filter(a -> "1".equals(a)).forEach(sj::add);
        int ret = s.length() - sj.toString().length();
        this.s = sj.toString();
        return ret;
    }
}
