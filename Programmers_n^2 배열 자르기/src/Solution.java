import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Solution {

    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        return LongStream.rangeClosed(left, right).mapToInt(i -> (int) Math.max(i / n, i % n) + 1).toArray();
    }

}
