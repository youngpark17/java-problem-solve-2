import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,r,c;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cnt=0;
        // n,n이면. (0~n/2,0~n/2), (0~n/2,n/2~n), (n/2~n,0~n/2), (n/2~n,n/2~n);
        int n = (int)Math.pow((int)Math.pow(2,N),2);
        partition(n,r,c);
        System.out.println(cnt);
    }
    public static void partition(int a, int x, int y){
        if(a==1){
            return;
        }
        int aa = (int)(Math.sqrt(a));
        if(x>=0 && x<aa/2 && y>=0 && y<aa/2){
            partition(a/4,x,y);
        }
        else if(x>=0 && x<aa/2 && y>=aa/2 && y<aa){
            cnt+=a/4;
            partition(a/4,x,y-(aa/2));
        }
        else if(x>=aa/2 && x<aa && y>=0 && y<aa/2){
            cnt+=a/2;
            partition(a/4,x-(aa/2),y);
        }
        else if(x>=aa/2 && x<aa && y>=aa/2 && y<aa){
            cnt+=(a/4)*3;
            partition(a/4,x-(aa/2),y-(aa/2));
        }

    }

}
