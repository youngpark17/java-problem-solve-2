import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    Set<String> set;
    List<String>[] listArr;
    Set<String> answer;

    public static void main(String[] args) {
        Solution so = new Solution();
        String[] a1 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] a2 = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(so.solution(a1, a2));

    }

    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        int cnt = 0;
        for (String s : banned_id) {
            String tmpS = s.replaceAll("[*]{1,1}", "[a-z|0-9]{1,1}");
            map.put(s + (cnt++), Arrays.stream(user_id).filter(a -> a.replaceAll("^"+tmpS+"$", "").length() == 0)
                    .collect(Collectors.toList()));
        }

        listArr = new List[map.size()];
        answer = new HashSet<>();
        cnt = 0;
        for (String s : map.keySet()) {
            listArr[cnt++] = map.get(s);
        }
        dfs(cnt, 0);
        return answer.size();
    }

    private void dfs(int a, int c) {
        if (c == a) {
            StringJoiner sj = new StringJoiner(",");
            set.stream().sorted().forEach(sj::add);
            answer.add(sj.toString());
            return;
        } else {
            for (String k : listArr[c]) {
                if (set.contains(k) == false) {
                    set.add(k);
                    dfs(a, c + 1);
                    set.remove(k);
                }
            }
        }
    }

}
