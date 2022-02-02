import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        long[] a = {2, 7};
        for (long k : so.solution(a)) {
            System.out.print(k + " ");
        }
    }

    public long[] solution(long[] numbers) {
        long[] answer = {};
        List<Long> ans = new ArrayList<>();
        for(long n : numbers){
            if(n%2==0){
                ans.add(n+1);
            }
            else{
                String padding = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                String str = Long.toBinaryString(n);
                str = padding.substring(0,64-str.length()) + str;
                char[] cs = str.toCharArray();
                for (int i = cs.length-1; i >= 0; i--) {
                    if(cs[i] == '0'){
                        cs[i] = '1';
                        if(i+1 <= cs.length-1){
                            cs[i+1] = '0';
                        }
                        break;
                    }
                }
                String k = new String(cs);
                ans.add(Long.parseLong(k,2));
            }
        }
        return ans.stream().mapToLong(Long::valueOf).toArray();
    }
}
