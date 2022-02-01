import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("1 2 3 4"));
    }

    public String solution(String s) {
        String answer = "";
        long[] arr = Arrays.stream(s.split(" ")).mapToLong(Long::parseLong).sorted().toArray();
        answer += arr[0]+" ";
        answer += arr[arr.length-1]+"";
        return answer;
    }
}
