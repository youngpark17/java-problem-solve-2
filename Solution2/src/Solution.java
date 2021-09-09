import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {

    boolean[] visited;
    int[] arr;
    Map<Integer,List<Integer>> needsMap;
    int maxCount=0;
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] needs =     {{ 1, 0, 0 }, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1} };
        int r =2;
        System.out.println(s.solution(needs,r));



    }

    public int solution(int[][] needs, int r) {
        int answer = 0;
        int len = needs.length;
        int len2 = needs[0].length;
        visited = new boolean[len2];
        needsMap= new HashMap<>(); //<완제품번호, 부품번호리스트>
        for (int i = 0; i < len; i++) {
            needsMap.put(i,new ArrayList<>());
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len2; j++) {
                if(needs[i][j] == 1){
                    needsMap.get(i).add(j);
                }
            }
        }
        // 조합으로 부품중에서 r개만큼 뽑아보자.
        combination(0,len2,r);
        answer = maxCount;
        return answer;
    }

    private void combination(int start, int n, int r) {
        if(r == 0) {
            //뽑고나서 각 부품을 가지고 있을때 만들 수 있는 완제품 수
            List<Integer> moduleList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if(visited[i]){
                    moduleList.add(i);
                }
            }
            int size = needsMap.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                List<Integer> needModule = needsMap.get(i);
                if(needModule.stream().allMatch(moduleList::contains)){
                    count+=1;
                }
            }
            maxCount = Math.max(count,maxCount);

            return;
        }
        for(int i=start; i<n; i++) {
            visited[i] = true;
            combination(i + 1, n, r - 1);
            visited[i] = false;
        }
    }



}
