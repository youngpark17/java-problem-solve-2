import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] A = {1,4,2};
        int[] B = {5,4,4};
        System.out.println(so.solution(A,B));
    }

    public int solution(int []A, int []B)
    {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int len = A.length;
        for (int i = 0; i < len; i++) {
            answer += A[i] * B[len-i-1];
        }
        return answer;
    }
}
