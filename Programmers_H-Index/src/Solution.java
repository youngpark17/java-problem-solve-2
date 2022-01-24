import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] c = new int[]{3,0,6,1,5};
        System.out.println(solution.solution(c));
    }
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int len = citations.length;
        int hIdx = 0;
        for (int i = 0; i < len; i++) {
            hIdx = len - i;
            if(citations[i]>=hIdx){
                answer = hIdx;
                break;
            }
        }
        return answer;
    }

}
