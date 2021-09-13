import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            char[] arr = br.readLine().toCharArray();
            int s = 0;
            int e = arr.length - 1;
            int ans = 0;
            boolean flag = false;
            while (s < e) {
                if (arr[s] != arr[e]) {
                    if (isPelindrome(arr, s + 1, e)) {
                        s++;
                        bw.append("1\n");
                        flag=true;
                    } else if (isPelindrome(arr, s, e - 1)) {
                        e--;
                        bw.append("1\n");
                        flag=true;
                    }
                    else{
                        bw.append("2\n");
                        flag=true;
                    }
                    break;
                }
                else{
                    s++;
                    e--;
                }
            }
            if(flag == false){
                bw.append("0\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isPelindrome(char[] arr, int s, int e) {
        while (s < e) {
            if (arr[s] != arr[e]) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
}
