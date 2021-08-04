public class Main {

    public static void main(String[] args) {

    }

    public int solution(String s, String t) {
        int result = 0;
        while(s.contains(t)){
            s = s.replaceAll(t,"");
            result++;
        }

        return result;
    }
}
