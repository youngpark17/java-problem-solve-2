import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            list.add(arr[i]-arr[i-1]);
        }
        Collections.sort(list);
        //k-1개 제거
        Integer[] answer = list.toArray(Integer[]::new);
        if(k>n){
            System.out.println(0);
        }
        else{
            System.out.println(Arrays.stream(Arrays.copyOfRange(answer, 0, answer.length - (k-1))).mapToInt(Integer::valueOf).sum());
        }

    }
}
