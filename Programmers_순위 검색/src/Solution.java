import java.util.*;
public class Solution {
    Map<String, List<Integer>> map;
    public static void main(String[] args) {
        Solution so = new Solution();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        for (int k : so.solution(info, query)) {
            System.out.println(k);
        }
    }


    public int[] solution(String[] infos, String[] query) {
        map = new HashMap<>();
        for(String info : infos){
            dfs(new StringBuilder(), info.split(" "), 0);
        }
        List<Integer> answer = new ArrayList<>();
        for(String key : map.keySet()){
            map.get(key).sort(Integer::compareTo);
        }
        for(String q : query){
            q = q.replaceAll(" and ","");
            String[] str = q.split(" ");
            if(map.containsKey(str[0])){
                int a = binarySearch(Integer.parseInt(str[1]), map.get(str[0]));
                answer.add(a);
            }
            else{
                answer.add(0);
            }

        }

        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int binarySearch(int sc, List<Integer> q){
        int start = -1;
        int last = q.size();
        int mid = 0;
        //최솟값 찾자
        while(start+1<last){
            mid = (start+last)/2;
            if(q.get(mid)>=sc){
                last = mid;
            }
            else{
                start = mid;
            }
        }
        return q.size()-last;
    }

    private void dfs(StringBuilder sb, String[] strs, int dep){
        if(dep == 4){
            if(map.containsKey(sb.toString()) == false){
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(strs[4]));
                map.put(sb.toString(),list);
            }
            else{
                map.get(sb.toString()).add(Integer.parseInt(strs[4]));
            }
        }
        else{
            sb.append(strs[dep]);
            dfs(sb,strs,dep+1);
            sb.delete(sb.length()-strs[dep].length(),sb.length());
            sb.append("-");
            dfs(sb,strs,dep+1);
            sb.delete(sb.length()-1, sb.length());
        }
    }

}
