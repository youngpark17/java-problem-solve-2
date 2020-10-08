import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] tarr;
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        tarr=new int[n];
        max = Integer.MIN_VALUE;
        permutation(0);
        System.out.println(max);

    }
    public static void permutation(int r){
        if(n==r){
            max = Math.max(max,getSum());
        }
        else{
            for (int i = 0; i < n; i++) {
                if(!visited[i]){
                    visited[i] = true;
                    tarr[r] = arr[i];
                    permutation(r+1);
                    visited[i] = false;
                }
            }
        }
    }

    public static int getSum(){
        int sum = 0;
        for (int i = 0; i <n-1; i++) {
            sum+=Math.abs(tarr[i]-tarr[i+1]);
        }
        return sum;
    }
}
