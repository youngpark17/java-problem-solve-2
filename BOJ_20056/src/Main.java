import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int sumMass[][];
    static int sumSpeed[][];
    static int sumNumber[][];
    static int[][] dir;
    static int[][] moveIdx = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static List<fireBall> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        sumMass = new int[n][n];
        sumSpeed = new int[n][n];
        dir = new int[n][n];
        sumNumber = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int dr = Integer.parseInt(st.nextToken())-1;
            int dc = Integer.parseInt(st.nextToken())-1;
            int dm = Integer.parseInt(st.nextToken());
            int ds = Integer.parseInt(st.nextToken());
            int dd = Integer.parseInt(st.nextToken());
            list.add(new fireBall(dr,dc,dm,ds,dd));
        }
        while(k-->0){
            for(fireBall ball : list){
                move(ball);
            }
            sumBall();
            list.clear();
            divide();
        }
        int total = 0;
        for(fireBall ball:list){
            total+=ball.m;
        }
        System.out.println(total);
    }
    public static void divide(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(sumNumber[i][j]>1){
                    int mass = sumMass[i][j]/5;
                    if(mass>0){
                        int speed = sumSpeed[i][j]/sumNumber[i][j];
                        if(dir[i][j]==100){ //1,3,5,7
                            list.add(new fireBall(i,j,mass,speed,1));
                            list.add(new fireBall(i,j,mass,speed,3));
                            list.add(new fireBall(i,j,mass,speed,5));
                            list.add(new fireBall(i,j,mass,speed,7));
                        }
                        else{ //0,2,4,6
                            list.add(new fireBall(i,j,mass,speed,0));
                            list.add(new fireBall(i,j,mass,speed,2));
                            list.add(new fireBall(i,j,mass,speed,4));
                            list.add(new fireBall(i,j,mass,speed,6));
                        }
                    }
                }
                else if(sumNumber[i][j]==1){
                    list.add(new fireBall(i,j,sumMass[i][j],sumSpeed[i][j],dir[i][j]));
                }

            }
        }
    }

    //같은 칸에 있는 볼을 합치자
    public static void sumBall(){
        //합치기 전에 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumMass[i][j]= 0;
                sumSpeed[i][j] = 0;
                sumNumber[i][j] = 0;
                dir[i][j] = -1;
            }
        }
        for(fireBall ball : list){
            sumMass[ball.r][ball.c]+=ball.m;
            sumNumber[ball.r][ball.c]+=1;
            sumSpeed[ball.r][ball.c]+=ball.s;
            if(dir[ball.r][ball.c]==-1 && ball.d%2==0){
                dir[ball.r][ball.c]=ball.d; //짝수 하나 들어옴
            }
            else if(dir[ball.r][ball.c]==-1 && ball.d%2==1){
                dir[ball.r][ball.c]=ball.d; //홀수 하나 들어옴
            }
            else if(dir[ball.r][ball.c]!=-1 && dir[ball.r][ball.c]%2==0 && ball.d%2==1 ||  dir[ball.r][ball.c]!=-1 &&dir[ball.r][ball.c]%2==1 && ball.d%2==0){ //홀수있는데 짝수들어옴 or 짝수있는데 홀수들어옴
                dir[ball.r][ball.c]=100;
            }
        }
    }
    public static void move(fireBall ball){
        int dr = moveIdx[ball.d][0];
        int dc = moveIdx[ball.d][1];
        int ds = ball.s;
        while(ds-->0){
            ball.r+=dr;
            ball.c+=dc;
            if(ball.r==-1){
                ball.r=n-1;
            }
            if(ball.r==n){
                ball.r=0;
            }
            if(ball.c==-1){
                ball.c=n-1;
            }
            if(ball.c==n){
                ball.c=0;
            }
        }

    }
}

class fireBall{
    int c,r,m,d,s;
    fireBall(int r,int c,int m,int s,int d){
        this.c=c;
        this.r=r;
        this.m=m;
        this.d=d;
        this.s=s;
    }
}