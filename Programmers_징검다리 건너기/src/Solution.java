public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1},3));
    }

    public int solution(int[] stones, int k) {
        int answer = 0;
        int h = Integer.MAX_VALUE-1;
        int l = 0;
        int m = 0;
        while(l+1<h){
            m = (h+l)/2;
            if(canGo(stones, m, k)){
                l = m;
            }
            else{
                h = m;
            }
        }
        return l;
    }

    private boolean canGo(int[] stones, int person, int k){
        int skip = 0;
        for(int stone : stones){
            if(stone-person <0){
                skip+=1;
                if(skip==k){
                    return false;
                }
            }
            else{
                skip = 0;
            }
        }
        return true;

    }
}
