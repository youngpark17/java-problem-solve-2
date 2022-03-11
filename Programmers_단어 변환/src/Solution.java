import java.util.*;

public class Solution {
    Set<String> set;

    static class Node {
        String c;
        int num;

        Node(String c, int num) {
            this.c = c;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("hit",	"cog",	new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        set = new HashSet<>();
        return bfs(begin, target, words);
    }

    private int bfs(String begin, String target, String[] words) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(begin, 0));
        while (!que.isEmpty()) {
            Node node = que.poll();
            if (node.c.equals(target)) {
                return node.num;
            }
            Arrays.stream(words).filter(w -> !set.contains(w) && canConvert(node.c, w))
                    .forEach(w -> {
                        set.add(w);
                        que.add(new Node(w, node.num + 1));
                    });
        }
        return 0;
    }

    private boolean canConvert(String a, String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int cnt = 0;
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            if (as[i] != bs[i]) {
                cnt++;
            }
        }
        if (cnt > 1) {
            return false;
        }
        return true;
    }
}
