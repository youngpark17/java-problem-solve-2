import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7662 {
    static int T;
    static int k;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            k = Integer.parseInt(br.readLine());
            TreeMap<Integer,Integer> treeMap = new TreeMap<>();

            for(int i=0; i<k; i++){
                StringTokenizer st=  new StringTokenizer(br.readLine());
                if(st.nextToken().equals("I")){
                    int value = Integer.parseInt(st.nextToken());
                    treeMap.put(value,treeMap.getOrDefault(value,0)+1);
                }
                else{
                    int value = Integer.parseInt(st.nextToken());
                    if(value==1 && treeMap.size()>0){
                        Map.Entry<Integer,Integer> entry = treeMap.lastEntry();
                        int key = entry.getKey();
                        int v = entry.getValue();
                        if(v>1){
                            treeMap.put(key,v-1);
                        }
                        else{
                            treeMap.remove(key);
                        }
                    }
                    else if(treeMap.size()>0){
                        Map.Entry<Integer,Integer> entry = treeMap.firstEntry();
                        int key = entry.getKey();
                        int v = entry.getValue();
                        if(v>1){
                            treeMap.put(key,v-1);
                        }
                        else{
                            treeMap.remove(key);
                        }
                    }
                }
            }
            if(treeMap.isEmpty()){
                System.out.println("EMPTY");
            }
            else{
                System.out.println(treeMap.lastEntry().getKey()+" "+treeMap.firstEntry().getKey());
            }

        }

    }
}
