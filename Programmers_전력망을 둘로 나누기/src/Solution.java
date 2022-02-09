import java.util.stream.IntStream;

public class Solution {
    int[] parents;
    public static void main(String[] args) {
        Solution so = new Solution();
        int n  = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(so.solution(n, wires));
    }
    public int solution(int n, int[][] wires) {
        int answer = 100;
        for(int[] wire : wires){
            wire[0]-=1;
            wire[1]-=1;
        }
        int skipIdx = 0;
        for (int a = 0; a < wires.length; a++) {
            parents = IntStream.range(0,n).toArray();
            for (int i = 0; i < wires.length; i++) {
                if(i != skipIdx){
                    union(wires[i][0],wires[i][1]);
                }
            }
            int tmp = 0;
            for (int j = 0; j < parents.length; j++) {
                if(find(parents[j]) != 0){ //작은게 부모인데, 부모가 0이 아니라는건... 다른 트리에있다는것
                    tmp ++;
                }
            }
            answer = Math.min(answer,Math.abs(n- tmp*2));
            skipIdx++;
        }

        return answer;
    }
    public int find(int k){
        if(parents[k] == k){
            return k;
        }
        return find(parents[k]);
    }

    private void union(int a, int b){
        int p1 = find(a);
        int p2 = find(b);
        if(p1==p2){
            return;
        }
        //p1이 더 큼
        int tmp = Math.min(p1,p2);
        p1 = Math.max(p1,p2);
        p2 = tmp;
        parents[p1] = p2;
    }
}
