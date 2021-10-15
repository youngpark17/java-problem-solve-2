import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args){
        Solution so = new Solution();
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31,10,45,1,6,19};
        for(int k: so.solution(lottos,win_nums)){
            System.out.println(k);
        }
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int countZero = 0;
        int countMatch = 0;
        List<Integer> winList = Arrays.stream(win_nums).boxed().collect(Collectors.toList());
        countMatch = (int)Arrays.stream(lottos).filter(winList::contains).count();
        countZero = (int)Arrays.stream(lottos).filter((lotto)->lotto == 0).count();
        int max = countMatch+countZero;
        int min = countMatch;
        max = getRank(max);
        min = getRank(min);
        int[] answer = {max,min};
        return answer;
    }
    public int getRank(int num){
        if(num == 6){
            return 1;
        }
        else if(num==5){
            return 2;
        }
        else if(num==4){
            return 3;
        }
        else if(num==3){
            return 4;
        }
        else if(num==2){
            return 5;
        }
        else{
            return 6;
        }
    }
}