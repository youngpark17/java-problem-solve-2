import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        int[] A = {0,0,1,1,2,2,2,3,3,3,3,3,97,98,99,100,100,100,100};

        int ans = Arrays.binarySearch(A,97);
        System.out.println(ans);
        int X = 5;
        System.out.println(solution(A,97));
    }

    public static int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] > X) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        l--;
        if (A[l] == X) {
            return l;
        }
        return -1;
    }
}
