import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());
        List<BigInteger> list =  new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String[] arr = br.readLine().split("[a-zA-Z]+");
            Arrays.stream(arr).forEach(a -> {
                if(!"".equals(a)){
                    list.add(new BigInteger(a));
                }
            });
        }
        Collections.sort(list);
        list.stream().forEach(a->{
            try{
                bw.append(a.toString()+"\n");
            }
            catch(Exception e){
                System.out.println(e);
            }

        });
        bw.flush();
        bw.close();
        br.close();
    }
}
