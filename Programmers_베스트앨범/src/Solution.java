
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String[] genres = {new String("classic"), new String("pop"), new String("classic"), new String("classic"), new String("pop")};
        int[] plays = {500, 600, 150, 800, 2500};
        Solution so = new Solution();
        Arrays.stream(so.solution(genres,plays)).forEach(a-> System.out.print(a+" "));
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String,Node> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if(!map.containsKey(genres[i])){
                Node n = new Node();
                n.pq.add(new InNode(plays[i],i));
                n.total+=plays[i];
                map.put(genres[i],n);
            }
            else{
                map.get(genres[i]).total+=plays[i];
                map.get(genres[i]).pq.add(new InNode(plays[i],i));
            }
        }
        List<Pair> list2 = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(String t : map.keySet()){
            list2.add(new Pair(t,map.get(t).total));
        }
        Collections.sort(list2,(a,b)->b.y-a.y);
        for(Pair t : list2){
            String x =t.x;
            PriorityQueue<InNode> pq = map.get(x).pq;
            int cnt=0;
            while(!pq.isEmpty() && cnt<2){
                cnt++;
                list.add(pq.poll().idx);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}

class Node{
    int total;
    PriorityQueue<InNode> pq;
    Node(){
        total = 0;
        pq = new PriorityQueue<>((a,b)->(a.num-b.num)==0? a.idx-b.idx : b.num-a.num);
    }
}

class InNode{
    int num;
    int idx;
    InNode(int num, int idx){
        this.num=num;
        this.idx=idx;
    }
}

class Pair{
    String x;
    int y;
    Pair(String x, int y){
        this.x=x;
        this.y=y;
    }
}


