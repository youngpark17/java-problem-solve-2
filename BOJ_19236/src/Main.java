import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    static boolean[] die;
    static int max = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] map;
        die = new boolean[17];
        map = new Fish[4][4];
        Xy[] xy = new Xy[17];
        for(int i=0; i<4; i++){
            StringTokenizer st =  new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                int number = Integer.parseInt(st.nextToken());
                int head = Integer.parseInt(st.nextToken())-1;
                map[i][j] = new Fish(number,head);
                xy[number] = new Xy(i,j,head);
            }
        }
        xy[0] = new Xy(0,0,0);
        move(map,xy,0,0,0,map[0][0].number);
        System.out.println(max);

    }

    public static void move(Fish[][] map,Xy[] xy, int sx, int sy, int sh, int eat){
        max = Math.max(eat,max);
        //상어가 먹자.
        xy[0].x=sx;
        xy[0].y=sy;
        int getNum = map[sx][sy].number;
        if(!die[getNum]){
            die[getNum]=true;
            sh = map[sx][sy].head;
            xy[0].z=sh;
            max = Math.max(eat,max);
            map[sx][sy].number=-1;
            xy[getNum].x=-1;
            xy[getNum].y=-1;
            xy[getNum].z=-1;
            map[sx][sy].head=-1;
        }
        //물고기 움직이자.

        for(int i=1; i<17;i++){
            if(!die[i]){
                int cx = xy[i].x;
                int cy = xy[i].y;
                int ch = xy[i].z;
                for(int j=0; j<8; j++){
                    int fx =cx+dx[(ch+j)%8];
                    int fy =cy+dy[(ch+j)%8];
                    map[cx][cy].head=(ch+j)%8;
                    xy[i].z = (ch+j)%8;
                        if(check(map,xy,cx,cy,fx,fy)){
                            //print(map);
                            //System.out.println();
                            break;
                        }
                }
            }
        }
        //상어 움직이자.
        while(sx<4 && sy<4 && sx>-1 && sy>-1){
            switch (sh){
                case 0:
                    sx-=1;
                    break;
                case 1:
                    sx-=1;
                    sy-=1;
                    break;
                case 2:
                    sy-=1;
                    break;
                case 3:
                    sx+=1;
                    sy-=1;
                    break;
                case 4:
                    sx+=1;
                    break;
                case 5:
                    sx+=1;
                    sy+=1;
                    break;
                case 6:
                    sy+=1;
                    break;
                case 7:
                    sx-=1;
                    sy+=1;
                    break;
            }
            if(sx>=0 && sy>=0 && sx<4 && sy<4){
                xy[0].x=sx;
                xy[0].y=sy;
                if(map[sx][sy].number!=-1 && !die[map[sx][sy].number]){
                    Fish[][] tmap = copy(map);
                    Xy[] txy = copy(xy);
                    move(tmap,txy, sx, sy, sh, eat+map[sx][sy].number);
                }
            }
        }
        die[getNum] = false;
    }

    public static void print(Fish[][] map){
        for(int i=0; i<4; i++){
            for (int j = 0; j <4; j++) {
                System.out.print(map[i][j].number+" ");
            }
            System.out.println();
        }
    }
    public static boolean check(Fish[][] map,Xy[] xy,int cx, int cy,int fx, int fy){
        if(fx<0 || fy<0 || fx>3 || fy>3) return false; //공간 벗어남.
        if(xy[0].x==fx && xy[0].y==fy) return false; //상어면 못바꿈.

        int currentNum = map[cx][cy].number;
        int currentH= map[cx][cy].head;
        int nextNum = map[fx][fy].number;
        int nextH = map[fx][fy].head;
        if(nextNum>0){
            map[cx][cy].number = nextNum;
            map[cx][cy].head = nextH;
            map[fx][fy].number = currentNum;
            map[fx][fy].head = currentH;
            xy[currentNum].x=fx;
            xy[currentNum].y=fy;
            xy[nextNum].x=cx;
            xy[nextNum].y=cy;
        }
        else{
            map[fx][fy].number=currentNum;
            map[fx][fy].head=currentH;
            xy[currentNum].x=fx;
            xy[currentNum].y=fy;
            map[cx][cy].number=-1;
            map[cx][cy].head=-1;
        }



        return true;

    }

    public static Fish[][] copy(Fish[][] map){
        Fish[][] tmp =new Fish[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < 4; j++) {
                Fish s = map[i][j];
                tmp[i][j] = new Fish(s.number,s.head);
            }
        }
        return tmp;
    }
    public static Xy[] copy(Xy[] xy){
        Xy[] tmp =new Xy[17];
        for (int i = 0; i < 17; i++) {
            Xy t = xy[i];
            tmp[i] = new Xy(t.x,t.y,t.z);
        }
        return tmp;
    }
}

class Xy{
    int x;
    int y;
    int z;
    Xy(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
}
class Fish{
    int number;
    int head;

    Fish(int number, int head){
        this.number=number;
        this.head=head;

    }
}
