
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static boolean[] visited;
    static int from,to;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            visited = new boolean[100001];
            Queue<Node> que = new LinkedList<>();
            visited[from] = true;
            Node node = new Node(from,"");
            que.add(node);
            while (!que.isEmpty()){
                Node out = que.poll();
                if(out.num==to){
                    bw.append(out.count+"\n");
                    break;
                }
                int k1 = order1(out.num);
                int k2 = order2(out.num);
                int k3 = order3(out.num);
                int k4 = order4(out.num);
                if(!visited[k1]){
                    visited[k1]=true;
                    que.add(new Node(k1,out.count+"D"));
                }
                if(!visited[k2]){
                    visited[k2]=true;
                    que.add(new Node(k2,out.count+"S"));
                }
                if(!visited[k3]){
                    visited[k3]=true;
                    que.add(new Node(k3,out.count+"L"));
                }
                if(!visited[k4]){
                    visited[k4]=true;
                    que.add(new Node(k4,out.count+"R"));
                }

            }

        }
        bw.flush();
        bw.close();
    }

    public static int order1(int num){
        int to = (num*2)%10000;
        return to;
    }

    public static int order2(int num){
        if(num==0){
            return 9999;
        }
        else{
            return num-1;
        }
    }

    public static int order3(int num){
        char[] to = (num+"").toCharArray();
        char[] tmpTo = new char[4];
        Arrays.fill(tmpTo,'0');
        int idxT=3;
        for (int i =to.length-1; i>=0 ; i--) {
            tmpTo[idxT--] = to[i];
        }
        char[] tmp = new char[4];
        Arrays.fill(tmp,'0');
        int idx=3;
        for (int i = 0; i <tmpTo.length; i++) {
            tmp[idx] = tmpTo[i];
            idx = (idx+1)%4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tmp);
        return Integer.parseInt(sb.toString());
    }
    public static int order4(int num){
        char[] to = (num+"").toCharArray();
        char[] tmpTo = new char[4];
        Arrays.fill(tmpTo,'0');
        int idxT=3;
        for (int i =to.length-1; i>=0 ; i--) {
            tmpTo[idxT--] = to[i];
        }
        char[] tmp = new char[4];
        Arrays.fill(tmp,'0');
        int idx=1;
        for (int i = 0; i<tmpTo.length ; i++) {
            tmp[idx] = tmpTo[i];
            idx = (idx+1)%4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tmp);
        return Integer.parseInt(sb.toString());
    }


}

class Node{
    int num;
    String count;
    Node(int num, String count){
        this.num=num;
        this.count=count;
    }
}

