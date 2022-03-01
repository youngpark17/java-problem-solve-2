import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Arrays.stream(new Solution().solution(2, 9)).forEach(System.out::println);
    }

    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if(s/n ==0){
            return new int[]{-1};
        }
        Arrays.fill(answer,s/n);
        if(s%n!=0) {
            for (int i = 0; i < s % n; i++) {
                answer[n - 1 - i] += 1;
            }
        }
        return answer;
    }
}
