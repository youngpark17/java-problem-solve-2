import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static int n;

    public static void main(String[] args) throws Exception{
        System.out.println(solution("40.0"));
        Math.pow
    }
    public static String solution(String t){
        BigDecimal decimal = new BigDecimal(t);
        List<BigDecimal> list = new ArrayList<>();
        //a < 35 <= b <=37.5 <c <=40.0 < d
        list.add(new BigDecimal("35.0")); //35 미만
        list.add(new BigDecimal("37.5")); //35 이상 37.5 이하
        list.add(new BigDecimal("40.0")); //37.5 초과 40.0 이하
        // compareto 더 크면 1 같으면 0 앞에가 더 작으면 -1
        if(decimal.compareTo(list.get(0))<0){
            return "hypothermia";
        }
        if(decimal.compareTo((list.get(0)))>=0 && decimal.compareTo(list.get(1))<=0){
            return "normal";
        }
        if(decimal.compareTo((list.get(1)))>0 && decimal.compareTo(list.get(2))<=0){
            return "fever";
        }
        if(decimal.compareTo(list.get(2))>0){
            return "hyperpyrexia";
        }

        return "";
    }


}
