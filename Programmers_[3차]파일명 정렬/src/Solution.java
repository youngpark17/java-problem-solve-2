import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Solution {
    static class Dto{
        String h;
        String m;
        String t;
        Dto(String h, String m, String t){
            this.h=h;
            this.m=m;
            this.t=t;
        }

        public int getM() {
            return Integer.parseInt(m);
        }

        public String getH() {
            return h.toLowerCase(Locale.ROOT);
        }

        public String getT() {
            return t;
        }
    }
    public static void main(String[] args) {
        Solution so = new Solution();
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        for(String k :so.solution(files)){
            System.out.println(k);
        }
    }

    public String[] solution(String[] files) {
        String[] answer = {};
        List<Dto> list = new ArrayList<>();
        for(String f : files){
            String[] tmp = f.split("\\d");
            String head = tmp[0];
            char[] cs = f.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = head.length(); i < cs.length; i++) {
                if((cs[i] - '0' >= 0 && cs[i] - '0' <= 9)){
                    sb.append(cs[i]);
                }
                else{
                    break;
                }
            }
            String mid = sb.toString();
            String last = f.substring(head.length()+mid.length());
            list.add(new Dto(head,mid,last));
        }
        list.sort(Comparator.comparing(Dto::getH)
                            .thenComparing(Dto::getM));
        return list.stream().map(a->a.h+a.m+a.t).toArray(String[]::new);
    }
}
