import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().replaceAll("pi|ka|chu","");
        if(s.length() == 0){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
