import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = "";
        while((input = br.readLine()) != null){
            int total = input.length();
            Pattern pattern1 = Pattern.compile("[a-z]{1}"); // 소문자만
            Pattern pattern2 = Pattern.compile("[A-Z]{1}"); // 대문자만
            Pattern pattern3 = Pattern.compile("[0-9]{1}"); // 숫자만
            Matcher matcher1 = pattern1.matcher(input);
            long len1 = matcher1.results().count();
            Matcher matcher2 = pattern2.matcher(input);
            long len2 = matcher2.results().count();
            Matcher matcher3 = pattern3.matcher(input);
            long len3 = matcher3.results().count();
            bw.append(String.format("%d %d %d %d\n",len1,len2,len3,total-(len1+len2+len3)));
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
