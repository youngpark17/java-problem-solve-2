import java.util.*;

public class Solution {
    Map<Integer, Map<String, Integer>> map;
    boolean[] visited;
    String[] orders;
    char[] tmp;
    char[] check;

    public static void main(String[] args) {
        Solution so = new Solution();
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        for(String k : so.solution(orders, course)){
            System.out.println(k);
        }

    }

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap();
        this.orders = orders;
        for (int c : course) {
            map.put(c, new HashMap<String, Integer>());
            for (String o : orders) {
                if (o.length() >= c) {
                    visited = new boolean[o.length()];
                    tmp = o.toCharArray();
                    Arrays.sort(tmp);
                    check = new char[c];
                    comb(o.length(), c, 0, 0);
                }
            }
        }
        List<String> list = new ArrayList<>();
        for (int c : course) {
            List<Map.Entry<String, Integer>> ll = new ArrayList<>();
            ll.addAll(map.get(c).entrySet());
            Collections.sort(ll, (a, b) -> b.getValue() - a.getValue());
            int k = 2;
            for (Map.Entry<String, Integer> e : ll) {
                if (e.getValue() >= k) {
                    k = e.getValue();
                    list.add(e.getKey());
                }
            }
        }
        Collections.sort(list);
        return list.toArray(String[]::new);
    }

    private void comb(int c, int r, int depth, int count) {
        if (count == r) {
            String a = new String(check);
            map.get(count).put(a, map.get(count).getOrDefault(a, 0) + 1);
        } else {
            for (int i = depth; i < c; i++) {
                if (visited[i] == false) {
                    visited[i] = true;
                    check[count] = tmp[i];
                    comb(c, r, i + 1, count + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
