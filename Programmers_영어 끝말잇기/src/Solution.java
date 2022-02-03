import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    LinkedHashSet<String> set;
    public static void main(String[] args) {
        Solution so = new Solution();
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        for(int k : so.solution(n,words)){
            System.out.println(k);
        }
    }

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int[] k = new int[n];
        set = new LinkedHashSet<>();

        for(int i=0; i<words.length; i++){
            k[i%n]++;
            if(words[i].length()<=1){
                answer[0] = (i+1)%n == 0? n : (i+1)%n;
                answer[1] = k[i%n];
                return answer;
            }
            if(set.contains(words[i])){
                answer[0] = (i+1)%n == 0? n : (i+1)%n;
                answer[1] = k[i%n];
                return answer;
            }
            if(i>0){
                if(words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)){
                    answer[0] = (i+1)%n == 0? n : (i+1)%n;
                    answer[1] = k[i%n];
                    return answer;
                }
            }
            set.add(words[i]);
        }
        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }
}
