import java.util.Arrays;
import java.util.Locale;
import java.util.StringJoiner;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(" 3people unFollowed me"));
    }

    public String solution(String s) {
        String answer = "";
        StringJoiner sj = new StringJoiner(" ");
        String[] tmp = s.split(" ");
        Arrays.stream(s.split(" ")).map(a -> a.toLowerCase(Locale.ROOT)).map(a -> toUp(a)).forEach(sj::add);
        return sj.toString().length() != s.length() ? sj.toString()+" " : sj.toString();
    }

    private String toUp(String s) {
        if (s.length() == 0 || s.charAt(0) - '0' < 10) {
            return s;
        }
        return String.format("%s%s", s.substring(0, 1).toUpperCase(Locale.ROOT), s.substring(1));
    }

}
