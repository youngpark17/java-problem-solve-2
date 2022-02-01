import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution so = new Solution();
        String m = "CC#BCC#BCC#";
        String[] musicinfos = {"03:00,03:08,FOO,CC#B"};
        System.out.println(so.solution(m,musicinfos));
    }
    class Node{
        int len;
        int k;
        String name;
        Node(int len, String name, int k){
            this.len = len;
            this.name = name;
            this.k = k;
        }
        public int getK(){
            return k;
        }
        public int getlen(){
            return len;
        }
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";
        List<Node> list = new ArrayList<>();
        int d=0;
        m = replace(m);
        for(String k : musicinfos){
            String[] arr  = k.split(",");
            LocalTime localTime = LocalTime.of(Integer.parseInt(arr[1].substring(0,2)),
                    Integer.parseInt(arr[1].substring(3,5)));
            LocalTime localTime2 = LocalTime.of(Integer.parseInt(arr[0].substring(0,2)),
                    Integer.parseInt(arr[0].substring(3,5)));

            int len = (localTime.getHour() - localTime2.getHour())*60 + localTime.getMinute()-localTime2.getMinute();
            StringJoiner sj = new StringJoiner("");
            arr[3] = replace(arr[3]);
            sj.add(arr[3]);
            String s = "";
            if(sj.length() /*음악길이*/ >= len /*재생된 시*/){
                s = sj.toString().substring(0,len);
            }
            else{
                while(sj.length()<len){
                    sj.add(arr[3]);
                }
                s = sj.toString().substring(0,len);
            }

            String regxStr = s.replaceAll(m,"");
            int totalLen = regxStr.length();

            if(totalLen != len){
                list.add(new Node(len, arr[2],d++));
            }
            list.sort(Comparator.comparing(Node::getlen)
                                .reversed()
                                .thenComparing(Node::getK));
        }

        return list.size() == 0? "(None)" : list.get(0).name;
    }

    private String replace(String m){
        m = m.replaceAll("C#","c");
        m = m.replaceAll("D#","d");
        m = m.replaceAll("F#","f");
        m = m.replaceAll("G#","g");
        m = m.replaceAll("A#","a");
        return m;
    }
}
