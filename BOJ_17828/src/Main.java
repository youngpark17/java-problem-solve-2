import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,x;
    static char[] arrs;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arrs = new char[n];
        Arrays.fill(arrs,'A');
        x-=n;
        if(x<0){
            System.out.println("!");
        }
        else{
            for (int i = n-1; i >=0 ; i--) {
                int min = Math.min(x,25);
                char c = (char)(min+(int)('A'));
                arrs[i] = c;
                x-=min;
                if(x<=0){
                    StringBuilder sb = new StringBuilder();
                    sb.append(arrs);
                    System.out.println(sb.toString());
                    break;
                }
            }
            if(x>0){
                System.out.println("!");
            }
        }
    }
}
