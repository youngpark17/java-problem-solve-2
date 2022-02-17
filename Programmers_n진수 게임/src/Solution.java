import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(2,4,2,1));
    }

    public String solution(int n, int t, int m, int p) {
        String answer = "";
        List<String> answerList = new ArrayList<>();
        StringJoiner sj = new StringJoiner("");
        int count = 0;
        while(sj.length()<=t*m+p){
            String s = Integer.toString(count,n);
            sj.add(s);
            count++;
        }
        String tmpS = sj.toString();
        for (int i = p-1; i < tmpS.length(); i+=m) {
            if(answerList.add(tmpS.substring(i,i+1)));
        }
        return answerList.stream().reduce(String::concat).get().toUpperCase(Locale.ROOT).substring(0,t);
    }
}
