import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int total = 0;
        //1은 다 더하자
        int oneCount = Arrays.stream(arr).filter(a -> a == 1).sum();
        total += oneCount;
        //0개수 구해놓고 음수 홀수개나오면 처리
        int zeroCount = (int) Arrays.stream(arr).filter(a -> a == 0).count();
        //양수, 홀수는 각각 두개씩 묶자
        List<Integer> list = Arrays.stream(arr).filter(a -> a != 0 || a != 1).boxed().collect(Collectors.toList());
        List<Integer> minusList = list.stream().filter(a -> a < 0).collect(Collectors.toList());
        List<Integer> plusList = list.stream().filter(a -> a > 0 && a != 1).collect(Collectors.toList());
        Collections.sort(minusList);
        Collections.sort(plusList, Collections.reverseOrder());
        int minusListSize = minusList.size();
        int plusListSize = plusList.size();
        if (minusListSize % 2 == 1 && zeroCount > 0) {
            minusList.remove(minusListSize - 1); //가장 작은 음수는 0이랑 곱해서 없앤다.
            minusListSize -= 1;
        }
        for (int i = 0; i < minusListSize; i += 2) {
            int a1 = minusList.get(i);
            int a2 = 1;
            if (i + 1 < minusListSize) {
                a2 = minusList.get(i + 1);
            }
            total += (a1 * a2);
        }
        for (int i = 0; i < plusListSize; i += 2) {
            int a1 = plusList.get(i);
            int a2 = 1;
            if (i + 1 < plusListSize) {
                a2 = plusList.get(i + 1);
            }
            total += (a1 * a2);
        }

        System.out.println(total);
    }
}
