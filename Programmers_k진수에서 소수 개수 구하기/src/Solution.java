public class Solution {
    public static void main(String[] args) {
     Solution so = new Solution();
     System.out.println(so.solution(437674,3));
    }
    public int solution(int n, int k) {
        int answer = 0;
        String s = changeToK(n,k);
        String[] sArr = s.split("0+");
        for(String ss : sArr){
            if(isPrime(Long.parseLong(ss))){
                answer++;
            }
        }
        return answer;
    }

    private boolean isPrime(long n){
        int sqtN = (int)Math.sqrt(n);
        if(n==1){
            return false;
        }
        for (int i = 2; i <= sqtN ; i++) {
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }

    private String changeToK(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n!=0){
            int r = n%k;
            sb.insert(0,r);
            n = n/k;
        }
        return sb.toString();
    }
}
