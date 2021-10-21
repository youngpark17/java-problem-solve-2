import java.util.*;

public class Solution {
    Map<Integer,Integer> map;
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] an = {2,7,11,15};
        int[] ans = so.twoSum(an,9);
        for(int aa : ans){
            System.out.println(aa);
        }
    }
    public int[] twoSum(int[] nums, int target) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i,nums[i]);
        }
        Arrays.sort(nums);
        int k = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int a = nums[i];
            int t = target-a;
            int idx = Arrays.binarySearch(nums,i+1,k,t);
            if(idx>0){
                int i1 = findIdx(a);
                map.remove(i1);
                int i2 = findIdx(t);
                list.add(i1);
                list.add(i2);
                break;
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int findIdx(int v){
        int k = 0;
        for(Map.Entry<Integer,Integer> en : map.entrySet()){
            if(en.getValue() == v){
                k = en.getKey();
                break;
            }
        }
        return k;
    }
}
