import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] move;
    static int[][][] ratio;
    static int cx;
    static int cy;
    static int outSand;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        outSand = 0;
        arr = new int[N][N];
        move = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        ratio = new int[][][]{{{5,0,-2},{10,1,-1},{10,-1,-1},{7,1,0},{7,-1,0},{2,-2,0},{2,2,0},{1,-1,1},{1,1,1}},
                {{5,2,0},{10,1,-1},{10,1,1},{7,0,1},{7,0,-1},{2,0,2},{2,0,-2},{1,-1,-1},{1,-1,1}},
                {{5,0,2},{10,1,1},{10,-1,1},{7,1,0},{7,-1,0},{2,-2,0},{2,2,0},{1,-1,-1},{1,1,-1}},
                {{5,-2,0},{10,-1,-1},{10,-1,1},{7,0,1},{7,0,-1},{2,0,2},{2,0,-2},{1,1,-1},{1,1,1}}};
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < N ; j++) {
                arr[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        // 중간 시작점은 N/2,N/2
        // 토네이도는 1칸이동후 방향 바뀜, 1칸 이동후 방향바뀜, 2칸 이동후 방향바뀜, 2칸이동후 방향바뀜, 3칸이동후...
        // i/2 i는 2부터 ++하면될듯하다.
        // i칸을 두번씩 이동 후 방향이 바뀌는 것을 알 수 있다.
        // 방향이동은 왼쪽, 아래, 오른쪽, 위 순이다.
        // {0,-1}, {1,0}, {0,1},{-1,0} 여기에 가중치를 곱하자.
        // y기준 좌표값 모래가 퍼지는 퍼센트. {5,0,-2},{10,1,-1},{10,-1,-1},{7,1,0},{7,-1,0},{2,-2,0},{2,2,0},{1,-1,1},{1,1,1} ... 이거 계산한거 다더한후, 원래 y의양에서 다뺸거
        cx = N/2;
        cy = N/2;
        int r = 0; // 방향
        int m = 1; // 가중치
        int tm = 1; //짝수일떄, 가중치증가
        while(cx !=0 || cy !=0){ // 토네이도가 계속 돌때까지.
            int moved = 1;
            while(moved<=m){ // 가중치 만큼 이동했다면 방향 바꾸자.'
                if(cx== 0 && cy == 0){
                    break;
                }
                cx +=move[r%4][0];
                cy +=move[r%4][1];
                int currentSand = arr[cx][cy];
                arr[cx][cy] = 0;
                //토네이도 이동완료 모래이동시키자.

                int tmpSand = 0; //a값 계산을 위해 모래 다 저장.
                for(int[] t : ratio[r%4]){
                    int ss = calcSand(currentSand,t[0]);
                    int nx = cx+t[1];
                    int ny = cy+t[2];
                    if(nx < N && ny < N && nx >=0 && ny >=0 ){
                        arr[nx][ny]+=ss;
                    }
                    else{
                        outSand+=ss;
                    }
                    tmpSand+=ss;
                }
                int aSand = currentSand-tmpSand;
                int ax = cx+move[r%4][0];
                int ay = cy+move[r%4][1]; //
                if(ay<0||ax<0||ax>N-1||ay>N-1){ //y로 옮겨가는 모래가 밖으로 나가는 모래라면
                    outSand+=aSand;
                }
                else{
                    arr[ax][ay]+=aSand;
                }
                moved++;
            }
            if(tm%2==0){
                m++;
            }
            tm++;
            r++;
        }
        System.out.println(outSand);
    }
    public static int calcSand(int currentSand, int per){
        return (int)(((currentSand*per)/100));
    }

}
