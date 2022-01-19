import java.time.LocalTime;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Solution {
    class Dto{
        String carNum;
        LocalTime time;
        boolean flag;
        Dto(String carNum, LocalTime time, boolean flag){
            this.carNum = carNum;
            this.time = time;
            this.flag = flag;
        }
        public String getCarNum(){
            return carNum;
        }
        public LocalTime getTime(){
            return this.time;
        }

    }

    class Ans{
        String carNo;
        int total;
        Ans(String carNo, int total){
            this.carNo = carNo;
            this.total = total;
        }
    }
    public static void main(String[] args) {
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] fees = {180, 5000, 10, 600};
        Solution so = new Solution();
        System.out.println(so.solution(fees, records));
    }
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        List<Dto> list = new ArrayList<>();
        for(String record : records){
            String[] arr  = record.split(" ");
            list.add(new Dto(arr[1], LocalTime.of(Integer.parseInt(arr[0].substring(0,2)),
                                                  Integer.parseInt(arr[0].substring(3,5))),
                            "IN".equals(arr[2]) ? true : false));
        }
        Map<String,List<Dto>> groupCarMap = list.stream()
                                                .collect(groupingBy(Dto::getCarNum));
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        List<Ans> ans = new ArrayList<>();
        for(Map.Entry<String,List<Dto>> entry : groupCarMap.entrySet()){
            entry.getValue().sort(Comparator.comparing(Dto::getTime));
            int totalTime = 0;
            int size = entry.getValue().size();
            int totalTimePerCar = 0;
            if(size%2 == 0){
                for (int i = 0; i < size-1; i+=2) {
                    Dto dto1 = entry.getValue().get(i);
                    Dto dto2 = entry.getValue().get(i+1);
                    LocalTime localTime = dto2.time.minusHours(dto1.time.getHour()).minusMinutes(dto1.time.getMinute());
                    int time = localTime.getHour()*60 + localTime.getMinute();
                    totalTimePerCar += time;
                }
            }
            else{
                for (int i = 0; i < size-1; i+=2) {
                    Dto dto1 = entry.getValue().get(i);
                    Dto dto2 = entry.getValue().get(i+1);
                    LocalTime localTime = dto2.time.minusHours(dto1.time.getHour()).minusMinutes(dto1.time.getMinute());
                    int time = localTime.getHour()*60 + localTime.getMinute();
                    totalTimePerCar += time;
                }
                Dto LastDto = entry.getValue().get(size-1);
                LocalTime lastLocalTime = LocalTime.of(23,59).minusHours(LastDto.time.getHour()).minusMinutes(LastDto.time.getMinute());
                totalTimePerCar += (lastLocalTime.getHour() * 60 + lastLocalTime.getMinute());
            }
            ans.add(new Ans(entry.getKey(),getAmount(fees, totalTimePerCar)));
        }
        ans.sort((a,b) -> a.carNo.compareTo(b.carNo));
        return ans.stream().mapToInt((a) -> a.total).toArray();
    }

    private int getAmount(int[] fees, int totalTime){
        if(totalTime<= fees[0]){
            return fees[1];
        }
        int totalMoney = fees[1];
        totalMoney += (int)Math.ceil((double)(totalTime - fees[0])/(double)fees[2]) * fees[3];
        return totalMoney;
    }
}
