public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("I"));
    }

    public int solution(String word) {
        int answer = 0;
        int total= 5+25+125+625+3125;
        String aeiou="AEIOU";

        for(String str: word.split("")){
            total/= 5;
            answer+= total*aeiou.indexOf(str)+1;
        }

        return answer;


    }
}
