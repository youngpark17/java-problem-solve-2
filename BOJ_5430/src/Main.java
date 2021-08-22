import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            char[] p = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            boolean flag = true; // true이면 앞에서부터 제거, false이면 뒤에서부터 제거
            boolean flag2 = true;
            Deque<String> deque = new LinkedList<>();
            String tmp = br.readLine().replaceAll("[\\[|\\]]", "");
            String[] tmpArr = new String[]{};
            if (tmp.length() > 0) {
                tmpArr = tmp.split(",");
            }
            Arrays.stream(tmpArr).forEach(deque::add);

            for (char c : p) {
                if (c == 'R') {
                    flag = !flag;
                } else {
                    if (deque.size() == 0) {
                        bw.append("error\n");
                        flag2 = false;
                        break;
                    } else if (flag) {
                        deque.removeFirst();
                    } else {
                        deque.removeLast();
                    }
                }
            }
            if (flag2) {
                String a = deque.stream().collect(Collectors.joining(",", "[", "]"));
                if(!flag){
                    Iterator<String> it = deque.descendingIterator();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    while(it.hasNext()){
                        sb.append(it.next());
                        if(it.hasNext()){
                            sb.append(",");
                        }
                    }
                    sb.append("]");
                    a = sb.toString();
                }
                bw.append(a+"\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
