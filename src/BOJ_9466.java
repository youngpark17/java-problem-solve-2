import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_9466 {
    static int n,T;
    static int[] arr;
    static int[] visited;
    static Set<Integer> set;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        while(T-->0){
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<n+1; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            visited = new int[n+1];
            set = new HashSet<>();
            for(int i=1; i<n+1; i++){
                if(visited[i]==0){
                    dfs(i);
                }
            }
            bw.append(n-set.size()+"\n");

        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int k){
        if(visited[k]==1){
            visited[k] = 2;
            set.add(k);
            if(visited[arr[k]]!=2) dfs(arr[k]);
            return;
        }
        visited[k] = 1;
        if(visited[arr[k]]!=2) dfs(arr[k]);
        visited[k] = 2;
    }


}
