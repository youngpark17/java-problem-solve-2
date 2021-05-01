import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long count = 0L;
        String[] s = br.readLine().split("[-| ]");
        count+= s.length;
        Pattern p = Pattern.compile("^(c|j|n|m|t|s|l|d|qu)'[aeiouh]");
        Matcher matcher;
        for(String k : s){
            matcher = p.matcher(k);
            Long c = matcher.results().count();
            count += c;
        }
        System.out.println(count);

    }
}
