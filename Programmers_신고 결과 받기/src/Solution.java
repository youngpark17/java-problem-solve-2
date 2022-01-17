import java.util.*;

public class Solution {
    //신고당한사람, 신고한사람리스트
    Map<String,Set<String>> map;
    public static void main(String[] args) {
        Solution so = new Solution();
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        map = new LinkedHashMap<>();
        for(String r : report){
            String[] rArr = r.split(" ");
            if(map.containsKey(rArr[1])){
                map.get(rArr[1]).add(rArr[0]);
            }
            else{
                Set<String> set = new HashSet<>();
                set.add(rArr[0]);
                map.put(rArr[1],set);
            }
        }
        Map<String,Integer> answer = new HashMap<>();
        for(Map.Entry<String,Set<String>> kv : map.entrySet()){
            if(kv.getValue().size()>=k){
                kv.getValue()
                        .stream()
                        .forEach(a-> answer.put(a,answer.getOrDefault(a,0)+1));
            }
        }
        return Arrays.stream(id_list).
                      mapToInt(id -> answer.getOrDefault(id,0))
                      .toArray();

    }
}
