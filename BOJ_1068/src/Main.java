import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int cnt=0;
    static int numbering=0;
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }
        int idx = 0;
        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < tmp.length; i++) {
            int p = Integer.parseInt(tmp[i]); //
            if(p==-1){
                idx=i;
            }
            else{
                graph[p].add(i);
            }
        }
        //idx가 root.
        t = Integer.parseInt(br.readLine());
        if(t!=idx) dfs(idx);
        System.out.println(cnt);

    }
    public static void dfs(int p){
        int sonNumber=0;
        for(int a : graph[p]){
            if(a!=t) {
                sonNumber++;
                dfs(a);
            }
        }
        if(sonNumber==0) cnt++;
    }
}
