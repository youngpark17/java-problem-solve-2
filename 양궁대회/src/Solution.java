import java.util.ArrayList;
import java.util.List;

public class Solution {
    int maxGap = -20000;
    List<int[]> answerList;
    int n;
    int size;
    public static void main(String[] args) {
        Solution so = new Solution();
        int n = 9;
        int[] info = {0,0,1,2,0,1,1,1,1,1,1};
        for(int a : so.solution(n,info)){
            System.out.print(a+" ");
        }
    }

    public int[] solution(int n, int[] info) {
        answerList = new ArrayList<>();
        this.n=n;
        int[] next = new int[info.length];
        this.size = info.length;
        comb(info, next, 0);
        if(maxGap <= 0){
            return new int[]{-1};
        }
        return getSmallNumHitCase();
    }

    private int[] getSmallNumHitCase() {
        answerList.sort((a,b) -> getLastIdx(a) - getLastIdx(b));
        return answerList.get(answerList.size()-1);
    }

    private int getLastIdx(int[] k){
        int sum = 0;
        for(int i = 0; i<size; i++){
            if(k[i]!=0){
                sum+=i*(int)Math.pow(1,i);
            }
        }
        return 0;
    }

    public void comb(int[] info, int[] next, int c){
        if(c == n){
            int gap = calcNumber(info, next);
            if(maxGap<=gap){
                maxGap = gap;
                this.answerList.add(next.clone());
            }
        }
        else{
            for (int i = 0; i < this.size && next[i]<=info[i]; i++) {
                next[i]+=1;
                comb(info, next, c+1);
                next[i]-=1;
            }
        }
    }

    private int calcNumber(int[] peachList, int[] lionList) {
        int peachNum = 0;
        int lionNum = 0;
        for (int i = 0; i < this.size; i++) {
            if (peachList[i] == 0 && lionList[i] == 0) {

            } else if (peachList[i] >= lionList[i]) {
                peachNum += this.size-1 - i;
            } else {
                lionNum += this.size-1 - i;
            }
        }
            return lionNum-peachNum;
    }
}
