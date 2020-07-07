import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k;
    static char[] num;
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        k = n-k; //k개를 뽑는 문제로 치환
        num = br.readLine().toCharArray();
        //분할정복으로 해보자. 0<=x<=n-k 사이에서 하나 뽑고 idx, 다시...
        int idx=0;
        int count=0;
        int start=0;
        int end = n-k;
        char[] answer = new char[k];
        while(k!=0){
            idx = findMaxIdx(start,end);
            answer[count++] = num[idx];
            k--;
            start = idx+1;
            end = n-k;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.println(sb.toString());

    }

    public static int findMaxIdx(int start, int end){
        int max=-1;
        int idx=-1;
        for (int i = start; i <= end; i++) {
            if(num[i]>max){
                max=num[i];
                idx=i;
            }
        }
        return idx;
    }
}

