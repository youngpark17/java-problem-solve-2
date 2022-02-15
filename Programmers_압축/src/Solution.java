import java.util.*;

public class Solution {
    Map<String,Integer> map;
    public static void main(String[] args) {
        Solution so = new Solution();
        Arrays.stream(so.solution("KAKAO")).forEach(System.out::println);
    }

    public int[] solution(String msg) {
        map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int cnt = 1;
        for (int i = 65; i < 91; i++) {
            char c = (char)i;
            map.put(String.valueOf(c),cnt++);
        }
        int s = 0;
        int e = 1;
        while(e <= msg.length()){
            String tmp = msg.substring(s,e);
            if(map.containsKey(tmp)){
                if(e==msg.length()){
                    list.add(map.get(tmp));
                    break;
                }
                e++;
            }
            else{
                String tmp2 = msg.substring(s,e-1);
                list.add(map.get(tmp2));
                map.put(tmp,cnt++);
                s=e-1;
            }
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
