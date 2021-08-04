import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String ss = "abcdefgea";
        String[] ans = solution(ss);
        for(String k : ans){
            System.out.println(k);
        }
    }
    public static String[] solution(String s) {
        List<String> list = new ArrayList<>();
        int len = s.length();
        int flag=0;
        int d=1;
        int chkLen=0;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i <(len/2)+1 ; i++) {
            String s1 = sb.substring(flag,i);
            String s2 = sb.substring((len)-flag-d++,(len)-flag);
            if(s1.equals(s2)){
                flag=i;
                d=1;
                chkLen+=s1.length()*2; //이걸로 다 안담아진게 있는지 체크.
                list.add(s1);
            }

        }
        int tmpSize = list.size();
        int lSize= tmpSize*2;
        boolean flag2 = chkLen!=len; //담아진거 *2한게 문자열의 길이와 다르다면
        String temp = "";
        int ll = (len-chkLen);
        if(flag2){
            if(ll==1){
                //aaabbbcbbbaaa substring(6,7) 길이는 13
                temp = sb.substring(len/2,len/2+ll);
                list.add(sb.substring(len/2,len/2+ll));
                lSize+=1;
            }
            else{
                if(ll%2==0){
                    ll=ll/2;
                    temp = sb.substring(len/2-ll,len/2+ll);
                    list.add(sb.substring(len/2-ll,len/2+ll));
                    lSize+=1;
                }
                else{
                    ll=ll/2;
                    temp = sb.substring(len/2-ll,len/2+ll+1);
                    list.add(sb.substring(len/2-ll,len/2+ll+1));
                    lSize+=1;
                }

            }
        }
        String[] answer = new String[lSize];
        for (int i = 0; i < tmpSize; i++) {
            answer[i] = list.get(i);
        }
        if(flag2){
            answer[tmpSize] = temp;
        }
        int ttt=0;
        if(flag2){
            for (int i = answer.length-1; i > tmpSize; i--) {
                answer[i] = list.get(ttt++);
            }
        }
        else{
            for (int i = answer.length-1; i >= tmpSize ; i--) {
                answer[i] = list.get(ttt++);
            }
        }
        return answer;
    }
}
