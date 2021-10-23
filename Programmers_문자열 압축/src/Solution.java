public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("xababcdcdababcdcd"));
    }

    public int solution(String s) {
        int n = s.length();
        int answer = s.length();

        for (int i = 1; i <= n/2; i++) {
            String prevStr = s.substring(0,i);
            String currentStr = "";
            StringBuilder sb = new StringBuilder();
            int j = i;
            while(j<=n){
                if(i+j>n){
                    currentStr = s.substring(j);
                }
                else{
                    currentStr = s.substring(j,i+j);
                }
                int cnt = 1;
                while(prevStr.equals(currentStr)){
                    prevStr = currentStr;
                    cnt++;
                    j+=i;
                    if(i+j>n || j >n){
                        currentStr = s.substring(j);
                    }
                    else{
                        currentStr = s.substring(j,j+i);
                    }
                }
                if(cnt!=1){
                    sb.append(String.format("%d%s",cnt,prevStr));
                }
                else{
                    sb.append(prevStr);
                }
                prevStr = currentStr;
                j+=i;
            }

            sb.append(currentStr);
            answer = Math.min(sb.toString().length(),answer);
        }
        return answer;
    }
}
