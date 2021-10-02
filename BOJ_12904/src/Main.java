import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static char[] t;
    static char[] s;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine().toCharArray();
        t = br.readLine().toCharArray();
        //t의 맨뒤가 b라면 b없애고 뒤집자
        //t의 맨뒤가 a라면 a제거
        int idx = t.length-1;
        while(s.length != t.length){
            if(t[idx] == 'A'){
                removeA();
            }
            else{
                removeB();
            }
            idx-=1;
        }
        boolean flag = true;
        for (int i = 0; i <t.length ; i++) {
            if(t[i] != s[i]){
                flag = false;
                break;
            }
        }
        if(flag == true){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
    public static void removeA(){
        t = Arrays.copyOfRange(t,0,t.length-1);
    }
    public static void removeB(){
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.copyOfRange(t,0,t.length-1));
        sb.reverse();
        t = sb.toString().toCharArray();
    }
}
