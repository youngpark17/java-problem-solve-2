import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args){
        int[] prices = {13000, 88000, 10000};
        int[] discounrts = {};
        int ans = solution(prices,discounrts);
        System.out.println(ans);
    }

    public static int solution(int[] prices, int[] discounts) {
        int answer = 0;
        //둘다 정렬 한 후 나누자.
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Arrays.stream(prices).forEach(a -> list1.add(a));
        Arrays.stream(discounts).forEach(a -> list2.add(a));
        Collections.sort(list1,Collections.reverseOrder());
        Collections.sort(list2,Collections.reverseOrder());
        int cpSize = list2.size();
        int size = list1.size();
        if(cpSize<=size){
            for (int i = 0; i < cpSize; i++) {
                answer+= (list1.get(i)*((100-list2.get(i)))/100);
            }
        }
        if(size>cpSize){
            for (int i = cpSize; i <size ; i++) {
                answer+=list1.get(i);
            }
        }

        return answer;
    }
}
