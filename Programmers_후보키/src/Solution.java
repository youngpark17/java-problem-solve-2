import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Solution {
    boolean[] visited;
    Map<Integer, Set<Integer>> map;
    List<int[]> list;
    String[][] relation;
    int rowLne;
    public static void main(String[] args) {
        Solution so = new Solution();
        String[][] r = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(so.solution(r));
    }

    public int solution(String[][] relation) {
        int answer = 0;
        map = new HashMap<>();
        this.relation = relation;
        list = new ArrayList<>();
        visited = new boolean[relation[0].length];
        rowLne = relation.length;
        for (int i = 0; i < relation[0].length; i++) {
            comb(relation[0].length,i+1,0,0);
        }
        return list.size();
    }

    public void comb(int n, int r, int c, int d){
        if(c==r){
            int[] arr = IntStream.rangeClosed(0, n-1).filter(i -> visited[i]).toArray();
            Set<String> set = new HashSet<>();
            //유일성
            for (int i = 0; i < rowLne; i++) {
                StringBuilder sb = new StringBuilder();
                for(int k : arr){
                    sb.append(relation[i][k]);
                }
                if(set.contains(sb.toString()) == false){
                    set.add(sb.toString());
                }
                else{
                    return ;
                }
            }
            for(int[] tmp : list){
                if(checkSubset(tmp, arr)){
                    return ;
                }
            }
            list.add(arr);
        }
        else{
            for (int i = d; i < n; i++) {
                if(visited[i] == false){
                    visited[i] = true;
                    comb(n, r, c+1, i);
                    visited[i] = false;
                }
            }
        }
    }

    public boolean checkSubset(int[] a1, int[] a2){
        int cnt = 0;
        for(int k : a1){
            if( Arrays.binarySearch(a2, k) >= 0){
                cnt++;
            }
        }
        if(cnt == a1.length){
            return true;
        }
        return false;
    }

}
