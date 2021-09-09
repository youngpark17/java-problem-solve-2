import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static int[] arr;
    static boolean[][] visited = {{false,false,false},{false,false,false},{false,false,false}};
    static Set<Integer> answerSet;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //자기 자신에게 빼고 가능
        answerSet = new TreeSet<>();
        int[] tmp = Arrays.copyOf(arr,3);
        dfs(2,arr[2],tmp);
    }
    public static void dfs(int start, int water, int[] tmp){
        for (int i = 0; i < 3; i++) {
            if(i!=start){
                if(!visited[start][i]){
                    visited[start][i] = true;

                }
            }
        }
    }
}
