import java.util.Arrays;

public class Solution {
    char[] arrs;
    String[] data;
    boolean[] visited;
    char[] choosed;
    int answer;

    public static void main(String[] args) {
        Solution so = new Solution();
        String[] data = {"N~F=0", "R~T>2"};
    }

    public int solution(int n, String[] data) {
        visited = new boolean[8];
        answer = 0;
        arrs = new char[8];
        char[] tmp = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        this.arrs = tmp;
        this.data = data;
        this.choosed = new char[8];
        com(0);
        return this.answer;
    }

    public void com(int r) {
        if (r == 8) {
            boolean checked = Arrays.stream(this.data).allMatch((c) -> checkCondition(c, choosed));
            if (checked == true) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                choosed[r] = arrs[i];
                com(r + 1);
                visited[i] = false;
            }

        }
    }


    public boolean checkCondition(String condition, char[] choosed) {
        char[] conditionArr = condition.toCharArray();
        char c1 = conditionArr[0];
        char c3 = conditionArr[2];
        char op = conditionArr[3];
        char checkNum = conditionArr[4];
        int i1 = indexOf(c1);
        int i2 = indexOf(c3);
        ;
        if (op == '=') {
            if (Math.abs(i1 - i2) -1 == checkNum - '0') {
                return true;
            }
        } else if (op == '>') {

            if (Math.abs(i1 - i2) - 1 > (checkNum - '0')) {
                return true;
            }
        } else if (op == '<') {
            if (Math.abs(i1 - i2) - 1 < (checkNum - '0')) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(char con) {
        for (int i = 0; i < choosed.length; i++) {
            if (choosed[i] == con) {
                return i;
            }
        }
        return -1;
    }
}