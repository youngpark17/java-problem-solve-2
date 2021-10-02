import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,l;
    static class Node{
        int s;
        int e;
        Node(int s, int e){
            this.s=s;
            this.e=e;
        }
    }
    static List<Node> nodeList;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList<>();
        while(n-->0){
            st = new StringTokenizer(br.readLine());
            nodeList.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        Collections.sort(nodeList,(a,b)->a.s-b.s != 0 ? a.s-b.s : a.e-b.e); //시작이 빠른건 종료가 빠른것
        if(nodeList.size()==1){
            int ans = nodeList.get(0).e-nodeList.get(0).s;
            if(ans%l==0){
                System.out.println(ans/l);
            }
            else{
                System.out.println((ans/l)+1);
            }
        }
        else{
            System.out.println(swip());
        }


    }
    public static int swip(){
        //웅덩이를 만나면 웅덩이 끝까지 널빤지를 깐다.
        //이때 널빤지 끝이 웅덩이를 초과할 수 있다. 그러면 웅덩이의 끝점을 널빤지의 끝으로 바꿔주자.
        int prevStart = nodeList.get(0).s;
        int prevEnd = nodeList.get(0).e;
        int ans = 0;
        int size = nodeList.size();
        for (int i = 1; i < size; i++) {
            Node node = nodeList.get(i);
            if(node.s<=prevEnd){
                prevEnd = Math.max(prevEnd,node.e);
                if(i == size-1){
                    ans += (prevEnd-prevStart)%l == 0? (prevEnd-prevStart)/l : ((prevEnd-prevStart)/l) +1;
                }
            }
            else{
                if((prevEnd-prevStart)%l == 0){
                    ans += (prevEnd-prevStart)/l;
                }
                else{
                    int add = ((prevEnd-prevStart)/l) +1;
                    ans += add;
                    prevEnd = prevStart+l*add;
                }
                prevStart  = Math.max(prevEnd,node.s);
                prevEnd = Math.max(prevEnd,node.e);
                if(i == size-1){
                    ans += (prevEnd-prevStart)%l == 0? (prevEnd-prevStart)/l : ((prevEnd-prevStart)/l) +1;
                }
            }
        }
        return ans;
    }
}
