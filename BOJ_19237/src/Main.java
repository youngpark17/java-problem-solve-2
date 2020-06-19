import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int cnt;
    static Smell[][] map;
    static Shark[] shark;
    static int[][][] priorty;
    static int timer;
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};
    static boolean[] die;
    public static void main(String[] args) throws Exception {
        timer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        die = new boolean[m+1];
        map = new Smell[n][n];
        shark = new Shark[m+1]; //0번 상어 없음.
        priorty = new int[m+1][5][4];
        cnt=m;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = new Smell(num,k);
                if(num!=0){
                    shark[num] = new Shark(num,i,j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<m+1; i++){
            shark[i].ro = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    priorty[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }
        while(cnt>1 && timer<=1000){
            //상어 이동.
            for(int i=1; i<m+1; i++){
                if(die[i]) continue;
                moveShark(i);
            }
            //시간
            timer();
            timer++;
            //상어 잡아먹자.
            eat();
            //영역 체크.
            check();
        }
        if(timer>1000){
            System.out.println(-1);
        }
        else{
            System.out.println(timer);
        }
    }

    public static void print(){

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(map[i][j].num+"/"+map[i][j].t +" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void check(){

        for(int i=1; i<m+1; i++){
            if(!die[i]){
                int x = shark[i].x;
                int y = shark[i].y;
                map[x][y].num=i;
                map[x][y].t=k;
            }
        }
    }

    public static void eat(){

        for(int i=1; i<m; i++){
            if(die[i]) continue;
            int cx = shark[i].x;
            int cy = shark[i].y;
            for (int j = i+1; j <m+1; j++){
                if(die[j]) continue;
                int nx = shark[j].x;
                int ny = shark[j].y;
                if(cx==nx & cy==ny){
                    cnt--;
                    die[j] = true;
                }
            }
        }

    }

    public static void timer(){
        for(int i=0; i<n; i++){
            for (int j = 0; j <n ; j++) {
                if(map[i][j].num>0){
                    map[i][j].t-=1;
                    if(map[i][j].t==0){
                        map[i][j].num=0;
                        map[i][j].t=k;
                    }
                }
            }
        }
    }

    public static void moveShark(int num){
        //빈칸이 있을경우.
        int[] nextStep = priorty[num][shark[num].ro];
        int cx = shark[num].x;
        int cy = shark[num].y;
        for(int i=0; i<4; i++){
            int nx=cx;
            int ny=cy;
            switch (nextStep[i]){
                case 1:
                    nx-=1;
                    break;
                case 2:
                    nx+=1;
                    break;
                case 3:
                    ny-=1;
                    break;
                case 4:
                    ny+=1;
                    break;
            }
            if(nx>=0 && ny>=0 && nx<n && ny<n){ //이동 가능하며.. 빈칸이면
                if(map[nx][ny].num==0){
                    shark[num].x=nx;
                    shark[num].y=ny;
                    shark[num].ro = nextStep[i];
                    return ;
                }
            }
        }
        //빈칸이 없다면 자신의 냄새가 있는칸.
        for(int i=0; i<4; i++){
            int nx=cx;
            int ny=cy;
            switch (nextStep[i]){
                case 1:
                    nx-=1;
                    break;
                case 2:
                    nx+=1;
                    break;
                case 3:
                    ny-=1;
                    break;
                case 4:
                    ny+=1;
                    break;
            }
            if(nx>=0 && ny>=0 && nx<n && ny<n){ //이동 가능하며.. 자신의 냄새가 있는 칸.
                if(map[nx][ny].num==num){
                    shark[num].x=nx;
                    shark[num].y=ny;
                    shark[num].ro = nextStep[i];
                    return ;
                }
            }
        }
    }

}

class Shark{
    int num;
    int ro;
    int x;
    int y;

    Shark(int num, int x, int y){
        this.num=num;
        this.x=x;
        this.y=y;
    }




}


class Smell{
    int num;
    int t;
    Smell(int num, int time){
        this.num=num;
        this.t=time;
    }

}
