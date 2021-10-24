import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("one4seveneight"));
    }
    public int solution(String s) {
        int answer = 0;
        Map<String,Integer> map = new HashMap<>();
        String[] keys = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for (int i = 0; i < 10; i++) {
            map.put(keys[i],i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if(s.charAt(i)-'0'>=10){
                String t1 = s.substring(i,i+3);
                if(map.containsKey(t1)){
                    sb.append(map.get(t1));
                    i+=3;
                }
                else{
                    String t2 = s.substring(i,i+4);
                    if(map.containsKey(t2)){
                        sb.append(map.get(t2));
                        i+=4;
                    }
                    else{
                        String t3 = s.substring(i,i+5);
                        if(map.containsKey(t3)){
                            sb.append(map.get(t3));
                            i+=5;
                        }
                    }
                }
            }
            else{
                sb.append(s.charAt(i));
                i++;
            }
        }

        return Integer.parseInt(sb.toString());
    }
}
