import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] arrs;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arrs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arrs);
        int s = Math.abs(arrs[0]);
        int e = Math.abs(arrs[arrs.length-1]);
        ans = 0;
        int startIdx = 0;
        int endIdx = arrs.length;
        if(s>e){ //음수의 절대값이 더 클때.
            ans +=s;
            //같은 부호인게 m개이상 있다면...
            long count = Arrays.stream(arrs).filter(arr -> arr<0).count();
            if(count>=m){
                startIdx+=m;
            }
            else{
                startIdx+=count;
            }

        }
        else{
            ans +=e;
            //같은 부호인게 m개이상 있다면...
            long count = Arrays.stream(arrs).filter(arr -> arr>=0).count();
            if(count>=m){
                endIdx-=m;
            }
            else{
                endIdx-=count;
            }

        }
        if(endIdx<=0 || startIdx>= arrs.length){
            System.out.println(ans);
        }
        else{
            arrs = Arrays.copyOfRange(arrs,startIdx,endIdx);
            getAnswer();
            System.out.println(ans);
        }


    }
    static void getAnswer(){

        for (int i = 0; i < arrs.length; i++) {
            int m1 = m;
            boolean flag = false;
            if(arrs[i]<0){
                int idx = i;
                while(m1-->0 && arrs[i]<0){
                    i+=1;
                    flag=true;
                    if(i== arrs.length){
                        break;
                    }
                }
                if(flag == true){
                    i-=1;
                }

                ans+=Math.abs(arrs[idx])*2;

            }
            else{
                break;
            }
        }
        for (int i = arrs.length-1; i >=0 ; i--) {
            int m1 = m;
            boolean flag = false;
            if(arrs[i]>=0){
                int idx = i;
                while(m1-->0 && arrs[i]>=0){
                    i-=1;
                    flag = true;
                    if(i<0){
                        break;
                    }
                }
                if(flag == true){
                    i+=1;
                }
                ans+=arrs[idx]*2;
            }
            else{
                break;
            }
        }
    }
}
