import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    Map<String,Integer> map;
    int[] parents;
    int[] totalMoney;
    public static void main(String[] args) {

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        Solution so = new Solution();
        for(int k : so.solution(enroll,referral,seller,amount)){
            System.out.println(k);
        }

    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        map = new HashMap<>();
        int cnt = 1;
        int len = enroll.length+1; //center까지
        parents = new int[len];
        totalMoney = new int[len];
        for(String en : enroll){
            if(map.containsKey(en) == false){
                map.put(en,cnt++);
            }
        }
        for (int i = 1; i < len; i++) {
            int key1 = map.get(enroll[i-1]);
            if("-".equals(referral[i-1]) == false){
                int key2 = map.get(referral[i-1]);
                union(key2,key1);
            }
            else{
                union(0,key1);
            }
        }
        int len2 = seller.length;
        for (int i = 0; i < len2; i++) {
            calcMoney(map.get(seller[i]),amount[i]*100);
        }
        answer = Arrays.copyOfRange(totalMoney,1,len);
        return answer;
    }

    public void calcMoney(int seller, int amount){
        if(seller == 0){
            totalMoney[0]+=amount;
            return;
        }
        else{
            totalMoney[seller]+=amount - (amount/10);
            calcMoney(parents[seller],amount/10);
        }
    }

    public int find(int k){
        if(parents[k] == k){
            return  k;
        }
        return find(parents[k]);
    }

    public void union(int parent, int b){
        parents[b] = parent;
    }

}
