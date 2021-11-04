import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String,Integer> map1;
    Map<String,Integer> map2;
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("E=M*C^2","e=m*c^2"));
    }
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        int n1 =  str1.length()-1;
        int n2 = str2.length()-1;
        for (int i = 0; i < n1; i++) {
            String k = str1.substring(i,i+2);
            k = k.replaceAll("[^a-z]","");
            if(k.length()==2){
                map1.put(k,map1.getOrDefault(k,0)+1);
            }

        }
        for (int i = 0; i < n2; i++) {
            String k = str2.substring(i,i+2);
            k = k.replaceAll("[^a-z]","");
            if(k.length()==2){
                map2.put(k,map2.getOrDefault(k,0)+1);
            }

        }
        double unionLen = getUnionLen();
        double diffLen = getDiffLen();
        if(unionLen == diffLen){
            return 65536;
        }
        double a = diffLen/unionLen;
        a = a * 65536;
        int answer = (int)Math.floor(a);

        return answer;
    }

    private int getDiffLen(){
        int len = 0;
        for(String key : map1.keySet()){
            if(map2.containsKey(key)){
                len += Math.min(map1.get(key),map2.get(key));
            }
        }
        return len;
    }

    public int getUnionLen(){
        int len = 0;
        for(String key : map1.keySet()){
            if(map2.containsKey(key)){
                len += Math.max(map1.get(key),map2.get(key));
            }
        }
        for(String key : map1.keySet()){
            if(map2.containsKey(key) == false){
                len += map1.get(key);
            }
        }
        for(String key : map2.keySet()){
            if(map1.containsKey(key) == false){
                len += map2.get(key);
            }
        }

        return len;
    }

}
