import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Node{
        int s;
        int e;
        Node(int s, int e){
            this.s=s;
            this.e=e;
        }
    }
    public static void main(String[] args) {
        Solution so = new Solution();
//        String[] lines = {"2016-09-15 01:00:04.001 2.0s",
//                "2016-09-15 01:00:07.000 2s",
//        };
//        String[] lines = {
//       "2016-09-15 01:00:04.002 2.0s",
//       "2016-09-15 01:00:07.000 2s"
//        };

        String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(so.solution(lines));
    }
    public int solution(String[] lines) {
        int answer = 0;
        final int TOTAL_TIME = 1000*60*60*24+1;
        List<Node> list = new ArrayList<>();
        for(String line: lines){
            int k = 0;
            String[] dates = line.split(" ");
            String[] times = dates[1].split(":");
            k+=Integer.parseInt(times[0])*60*60*1000;
            k+=Integer.parseInt(times[1])*60*1000;
            k+=Integer.parseInt(times[2].substring(0,2))*1000;
            k+=Integer.parseInt(times[2].substring(3));
            int wastedTime = Integer.parseInt(dates[2].substring(0,1))*1000; // + Integer.parseInt(dates[2].substring(2, dates[2].length()-1));
            if(dates[2].length()>3){
                wastedTime += Integer.parseInt(dates[2].substring(2, dates[2].length()-1));
            }
            int start = k-wastedTime+1;
            int end = k;
            list.add(new Node(start,end));
        }

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            int s = node.s;
            int e = node.e;
            int a = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(i==j){
                    continue;
                }
                int ss = list.get(j).s;
                int ee = list.get(j).e;
                if(s+1000>ss){
                    a++;
                }
            }
            answer = Math.max(answer,a);
        }

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            int s = node.s;
            int e = node.e;
            int a = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(i==j){
                    continue;
                }
                int ss = list.get(j).s;
                int ee = list.get(j).e;
                if(e+1000>ss){
                    a++;
                }
            }
            answer = Math.max(answer,a);
        }
        return answer;
    }
}