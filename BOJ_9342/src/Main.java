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
        int k = Integer.parseInt(br.readLine());
        String s = "";

        Pattern pattern = Pattern.compile("^[A|B|C|D|E|F]{0,1}A+F+C+[A|B|C|D|E|F]{0,1}$");
        Matcher matcher;
        while(k-->0){
            s = br.readLine();
            matcher = pattern.matcher(s);
            if(matcher.find()){
                bw.append("Infected!\n");
            }
            else{
                bw.append("Good\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
