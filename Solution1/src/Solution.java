import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] deposits = {"01/01 2 50000", "01/03 1 999", "01/31 9 10000", "02/05 5 6547", "02/05 6 1", "06/30 5 5000", "10/15 5 2529", "12/30 4 10000"};

        System.out.println(s.solution(deposits));

    }

    public int solution(String[] deposits) {
        int answer = 0;
        for(String deposit : deposits){
            String[] depArr = deposit.split(" ");
            double dateCalc = getDateCounter(depArr[0]);
            double its = Double.parseDouble(depArr[1])/100;
            int money = Integer.parseInt(depArr[2]);
            long fixedIts = (long)Math.floor(money*dateCalc*its);
            answer += money+fixedIts;

        }
        return answer;
    }

    private double getDateCounter(String mmdd){
        String month = mmdd.substring(0,2);
        int day = Integer.parseInt(mmdd.substring(3,5));
        int date = 0;
        switch (month){
            case "01":
                date +=31;
            case "02":
                date +=28;
            case "03":
                date +=31;
            case "04":
                date+=30;
            case "05":
                date+=31;
            case "06":
                date+=30;
            case "07":
                date+=31;
            case "08":
                date+=31;
            case "09":
                date+=30;
            case "10":
                date+=31;
            case "11":
                date+=30;
            case "12":
                date+=31;
                break;
        }
        date -=day;
        return (double)((date*1.0)/365*1.0);
    }

}
