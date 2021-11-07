import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    private static final int HIT_RUN_TIME = 1;
    private static final int MISS_RUN_TIME = 5;

    public static void main(String[] args) {
        Solution so = new Solution();
        int cacheSize = 5;
        String[] cities = {"Seoul", "Seoul", "Seoul"};

        System.out.println(so.solution(cacheSize, cities));
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Set<String> cacheSet = new LinkedHashSet<>();
        if (cacheSize == 0) {
            return cities.length * MISS_RUN_TIME;
        }
        cities = Arrays.stream(cities).map(String::toUpperCase).collect(Collectors.toList()).toArray(String[]::new);
        for (String city : cities) {
            if (cacheSet.contains(city)) {
                answer += HIT_RUN_TIME;
                cacheSet.remove(city);
                cacheSet.add(city);
            } else {
                answer += MISS_RUN_TIME;
                if (cacheSet.size() < cacheSize) {
                    cacheSet.add(city);
                } else {
                    String firstInCity = cacheSet.stream().findFirst().get();
                    cacheSet.remove(firstInCity);
                    cacheSet.add(city);
                }
            }
        }
        return answer;
    }
}
