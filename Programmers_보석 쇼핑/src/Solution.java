import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    Set<String> set;
    public static void main(String[] args) {
        Solution so = new Solution();
        Arrays.stream(so.solution(new String[]{"A","A","B"})).forEach(System.out::println);
    }

    public int[] solution(String[] gems) {
        int[] answer = {};
        set = Arrays.stream(gems).collect(Collectors.toSet());;
        //0,1,2,3 세팅
        Map<String,Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        List<int[]> list = new ArrayList<>();
        int cnt = 0;
        while(start <= end && end <=gems.length){
            if(map.keySet().size()==set.size()){
                map.put(gems[start],map.get(gems[start])-1);
                if(map.get(gems[start]) == 0){
                    map.remove(gems[start]);
                }
                start++;
            }
            else{
                if(end == gems.length){
                    break;
                }
                map.put(gems[end],map.getOrDefault(gems[end],0)+1);
                end++;
            }
            if(map.keySet().size()==set.size()){
                int[] ans = {start+1,end};
                list.add(ans);
            }

        }

        list.sort((a,b) -> (a[1]-a[0]) - (b[1]-b[0]) != 0 ? (a[1]-a[0]) - (b[1]-b[0]) : a[0]-b[0] );

        return list.get(0);
    }

}
