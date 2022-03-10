import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(2,10,2,new String[]{"09:10", "09:09", "08:00"}));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        Deque<String> que = new ArrayDeque(Arrays.asList(timetable).stream().sorted().collect(Collectors.toList()));
        String currentTime = "09:00";
        String answer = "";

        for (int i = 0; i < n; i++) {
            if(i!=0) {
                currentTime = getNextTime(currentTime, t);
            }
            int cnt = m;
            while(cnt > 0 && !que.isEmpty() && i!=n-1){
                if(isSmallSame(que.peekFirst(),currentTime)){
                    que.pollFirst();
                    cnt--; //승객한명탑승
                }
                else{
                    break;
                }
            }
            if(i == n-1 ){
                if(que.isEmpty()){
                    return currentTime;
                }
                else{
                    for(String time : que){
                        if(cnt==1){
                            break;
                        }
                        if(isSmall(time,currentTime)){
                            que.pollFirst();
                            cnt--; //승객한명탑승
                        }
                    }
                }
                return que.isEmpty()?
                        currentTime.substring(0,2)+":"+String.format("%02d",(Integer.parseInt(currentTime.substring(3,5)))) :
                        (isSmallSame(que.peekFirst(),currentTime) == false ? currentTime : getMinusTime(que.peekFirst()));
            }

        }

        return answer;
    }

    private String getMinusTime(String currentTime){
        int currentHour = Integer.parseInt(currentTime.substring(0,2));
        int currentMinute =Integer.parseInt(currentTime.substring(3,5))-1;
        if(currentMinute<0){
            currentMinute = 60+currentMinute;
            currentHour-=1;
        }
        return String.format("%02d:%02d",currentHour,currentMinute);
    }

    private String getNextTime(String currentTime, int t){
        int offSetHour = (t/60);
        int offSetMinute = (t%60);
        int currentHour = Integer.parseInt(currentTime.substring(0,2))+offSetHour;
        int currentMinute =Integer.parseInt(currentTime.substring(3,5))+offSetMinute;
        return String.format("%02d:%02d",currentHour,currentMinute);
    }

    private boolean isSmallSame(String time1, String base){
        if(time1.compareTo(base)<=0){
            return true;
        }
        return false;
    }

    private boolean isSmall(String time1, String base){
        if(time1.compareTo(base)<0){
            return true;
        }
        return false;
    }
}
