import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] heights;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        heights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int[] arr = new int[n];

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a  = Integer.parseInt(st.nextToken())-1;
            int b  = Integer.parseInt(st.nextToken())-1;
            int c  = Integer.parseInt(st.nextToken());
            arr[a] +=c;
            if(b+1<n){
                arr[b+1] -=c;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1]+arr[i];
        }
        for (int i = 0; i < n; i++) {
            heights[i]+=arr[i];
            bw.append(heights[i]+" ");
        }
        bw.flush();
        bw.close();

    }
}
