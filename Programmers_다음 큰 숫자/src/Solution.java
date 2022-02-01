public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(78));
    }
    public int solution(int n) {
        int answer = 0;
        int k = Integer.bitCount(n);
        for (int i = n+1; i < 1_000_001; i++) {
            if(k==Integer.bitCount(i)){
                return i;
            }
        }
        return answer;
    }
}
