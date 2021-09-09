import java.util.*;
import java.util.stream.Collectors;

public class Main {

    boolean[] visited;
    char[] arr;
    Set<String> set;
    List<String> ans;

    public static void main(String[] args) {
        Main m = new Main();
        String[] a = m.solution("abcbc");
        for (String kk : a) {
            System.out.print(kk + " ");
        }
    }

    public String[] solution(String letters) {
        visited = new boolean[letters.length()];
        arr = letters.toCharArray();
        set = new HashSet<>();
        int n = arr.length;
        char[] output = new char[n];
        perm(arr, output, 0, n);
        ans = set.stream().collect(Collectors.toList());
        Collections.sort(ans);
        int size = ans.size();
        String[] answer = new String[size];
        for (int i = 0; i < size; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    void perm(char[] arr, char[] output, int depth, int n) {
        if (depth == n) {
            StringBuffer sb = new StringBuffer();
            sb.append(output);
            set.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                if (depth == 0) {
                    visited[i] = true;
                    output[depth] = arr[i];
                    perm(arr, output , depth + 1, n);
                    visited[i] = false;

                } else {
                    if(output[depth-1]!=arr[i]) {
                        visited[i] = true;
                        output[depth] = arr[i];
                        perm(arr, output, depth + 1, n);
                        visited[i] = false;
                    }
                }
            }
        }
    }


}
