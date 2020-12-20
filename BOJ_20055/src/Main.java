import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[] belt;
    static boolean[] beltFlag;
    static int flag1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[2*n];
        beltFlag = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n*2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        flag1=0;
        //벨트 회전
        // ... 벨트회전하면 로봇한칸씩 움직인다.
        //로봇 움직임
        // ...먼저 올라온 로봇부터(뒤에 있는 로봇부터 움직이면된다)이동할 수 있다면(내구도 1이상, 앞에로봇x) 한칸 이동한다.
        //로봇 올림
        // ...내구도가 1이상이라면 로봇을 올린다.
        //내구도 0인 칸 파악하자.
        //boolean flag = false;
        int round=0;
        while(true){
            round++;
            rotateBelt();
            moveRobot();
            if(flag1>=k){
                break;
            }
        }
        System.out.println(round);
    }
//    public static boolean check(){
//        int check=0;
//        for (int i = 0; i < 2*n; i++) {
//            if(belt[i]<=0){
//                check++;
//            }
//        }
//        return check==k? true:false;
//    }

    public static void moveRobot(){
        beltFlag[n-1] = false;
        for (int i = n-2; i >=0; i--) {
            if(beltFlag[i]&&!beltFlag[i+1] && belt[i+1]>0){ //로봇이 있고, 앞에공간이 있으며, 내구도가 1이상일떄
                belt[i+1]-=1;
                if(belt[i+1]==0){
                    flag1++;
                }
                beltFlag[i+1] = true;
                beltFlag[i] = false;
            }
        }
        if(!beltFlag[0] && belt[0]>0){
            beltFlag[0] = true;
            belt[0]-=1;
            if(belt[0]==0){
                flag1++;
            }
        }
    }

    public static void rotateBelt() {
        //벨트 회전하면 내구도 한칸씩 앞으로...
        int[] tmpBelt = new int[2*n];
        for (int i = 0; i < 2*n; i++) {
            int nextIdx = (i+1)%(2*n);
            tmpBelt[nextIdx] = belt[i];
        }
        //로봇이 원래 자리에 있었다면 로봇도 한칸 앞으로
        beltFlag[n-1] = false; //마지막은 무조건 내려감.
        for (int i = n-1; i >0; i--) {
            beltFlag[i] = beltFlag[i-1];
        }
        beltFlag[0] = false; //로봇이 있는채로 올라오진 않으니까 일단 false;
        belt = tmpBelt;
    }
}
