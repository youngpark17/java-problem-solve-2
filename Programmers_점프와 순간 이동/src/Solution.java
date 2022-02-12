public class Solution {

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(5));
    }
    public int solution(int n) {
        int cnt = 0;
        for(char c :Integer.toBinaryString(n).toCharArray()){
            if(c=='1'){
                cnt++;
            }
        }
        return cnt;
    }
}
