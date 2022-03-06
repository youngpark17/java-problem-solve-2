import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws  Exception{
        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        String play_time = "99:59:59";
        String adv_time = "25:00:00";

        Solution so = new Solution();
        System.out.println(so.solution(play_time, adv_time, logs));
    }

    public String solution(String play_time, String adv_time, String[] logs) throws Exception {
        FileWriter fw = new FileWriter("./name");

        String answer = "";
        int total = getSecond(play_time);
        int advSecond = getSecond(adv_time);
        long[] lines = new long[total+1];
        for(String log : logs){
            String[] arr = log.split("-");
            int start = getSecond(arr[0]);
            int end = getSecond(arr[1]);
            lines[start]+=1;
            lines[end]-=1;
        }
        for (int i = 1; i <= total; i++) {
            lines[i] += lines[i-1];
        }
        for (int i = 1; i <= total; i++) {
            lines[i] += lines[i-1];
        }
        int answerSecond = 0;
        long max = lines[advSecond-1];

        int idx = 0;
        for (int i = 0; i+advSecond <= total; i++) {
            if(max < lines[i+advSecond] - lines[i]){ //advSecond동안의 누적 접속자수
                max = lines[i+advSecond] - lines[i];
                idx = i+1;
            }
        }

        return getTime(idx);
    }

    private String getTime(int second){
        int h = second/(60*60);
        int m = (second-(h*60*60))/60;
        int s = (second-(h*60*60)-m*60);
        return String.format("%02d:%02d:%02d",h,m,s);
    }

    private int getSecond(String time){
        int second = 0;
        String[] times = time.split(":");
        second += Integer.parseInt(times[0])*60*60;
        second += Integer.parseInt(times[1])*60;
        second += Integer.parseInt(times[2]);
        return second;
    }

}
