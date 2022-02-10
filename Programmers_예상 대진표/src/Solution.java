public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(8,4,5));
    }

    public int solution(int n, int a, int b) {
        int answer = 0;
        while (Math.abs(a-b) >= 1) {
            answer++;
            a = a/2 + (int)Math.ceil(a%2);
            b = b/2 + (int)Math.ceil((b%2));
        }
        return answer;
    }
}
