import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static boolean[] isPrimeNumber;
    static boolean[] visited;
    static int c;
    static char[] numbers;
    static List<Character> list;
    static boolean[] visited2;
    static Deque<Character> deque;
    static int ans;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        isPrimeNumber = new boolean[10000000];
        c = Integer.parseInt(br.readLine());
        Arrays.fill(isPrimeNumber, true);
        getAllPrimeNumber();

        for (int i = 0; i < c; i++) {
            numbers = br.readLine().toCharArray();
            //0~numbers.length 만큼 뽑아서 순열할때...
            visited = new boolean[numbers.length];
            ans = 0;
            set = new HashSet<>();
            for (int j = 1; j <= numbers.length; j++) {
                comb(numbers.length, 0, 0, j);
            }
            for (int se : set) {
                if (isPrimeNumber[se] == true && se > 1){
                    ans++;
                }
            }
            bw.append(ans + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void per(int n, int r, int total) {
        if (r == total) {
            StringBuilder sb = new StringBuilder();
            for (char cc : deque) {
                sb.append(cc);
            }
            int k = Integer.parseInt(sb.toString());
            set.add(k);
        } else {
            for (int i = 0; i < n; i++) {
                if (visited2[i] == false) {
                    visited2[i] = true;
                    deque.addLast(list.get(i));
                    per(n, r + 1, total);
                    deque.removeLast();
                    visited2[i] = false;
                }
            }
        }
    }

    public static void comb(int n, int depth, int r, int total) {
        if (r == total) {
            //조합인 상태에서 순열.
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    list.add(numbers[i]);
                }
            }
            visited2 = new boolean[list.size()];
            deque = new ArrayDeque<>();
            per(list.size(), 0, r);

        } else {
            for (int i = depth; i < n; i++) {
                if (visited[i] == false) {
                    visited[i] = true;
                    comb(n, i + 1, r + 1, total);
                    visited[i] = false;
                }
            }
        }
    }

    public static void getAllPrimeNumber() {
        int sqrtn = (int) Math.sqrt(10000000);
        for (int i = 2; i <= sqrtn; i++) {
            if(isPrimeNumber[i] == false){
                continue;
            }
            for (int j = i * i; j < 10000000; j += i) {
                isPrimeNumber[j] = false; //소수가 아니면 false;
            }
        }
    }
}
