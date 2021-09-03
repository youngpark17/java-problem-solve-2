import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[][] map;
    static Map<Integer, List<Integer>> stLikeList;
    static Map<Integer, Node1> isSitted;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        stLikeList = new LinkedHashMap<>();
        isSitted = new HashMap<Integer, Node1>();

        for (int i = 0; i < n * n; i++) {
            String[] tmpArr = br.readLine().split(" ");
            stLikeList.put(Integer.parseInt(tmpArr[0]), Arrays.stream(tmpArr).map(Integer::parseInt).collect(Collectors.toList()));
        }
        for (int st : stLikeList.keySet()) {
            List<Integer> likeList = stLikeList.get(st);
            List<Integer> likeAndSitList = likeList.stream().filter(isSitted::containsKey).collect(Collectors.toList());
            List<Node1> rstCon1;
            if (likeAndSitList.size() > 0) { // con1부터 체크
                rstCon1 = checkCon1(st, likeAndSitList);
                if (rstCon1.size() == 1) {
                    isSitted.put(st, rstCon1.get(0));
                } else { //좋아하는 학생중에서 인접한칸
                    List<Node1> rstCon2 = checkCon2(st, rstCon1);
                    rstCon2.sort((a, b) -> a.x - b.x == 0 ? a.y - b.y : a.x - b.x);
                    isSitted.put(st, rstCon2.get(0));
                }
            } else { // 좋아하는 학생이 앉아있지 않다면 인접한칸중 빈칸젤 많고 row가 젤 작은걸로 쉽게 구할수 있다.
                int[][] arr = new int[n][n];
                for (Node1 node1 : isSitted.values()) {
                    arr[node1.x][node1.y] = -1;
                }
                List<Node1> rstList = new ArrayList<>();
                int max = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] != -1) {
                            for (int k = 0; k < 4; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];
                                if (isIn(nx, ny)) {
                                    if (arr[nx][ny] != -1) {
                                        arr[i][j] += 1;
                                        max = Math.max(max, arr[i][j]);
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] == max) {
                            rstList.add(new Node1(i, j));
                        }
                    }
                }

                rstList.sort((a, b) -> a.x - b.x == 0 ? a.y - b.y : a.x - b.x);
                isSitted.put(st, rstList.get(0));
            }
        }
        System.out.println(calcMap());
    }
    public static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < n;
    }

    public static int calcMap() {

        for (int std : isSitted.keySet()) {
            Node1 node1 = isSitted.get(std);
            map[node1.x][node1.y] = std;
        }
        int ans = 0;
        for (int kk : isSitted.keySet()) {
            List<Integer> list = stLikeList.get(kk);
            int tmp = 0;
            for (int k = 0; k < 4; k++) {
                int nx = isSitted.get(kk).x + dx[k];
                int ny = isSitted.get(kk).y + dy[k];
                if (isIn(nx, ny)) {
                    if (list.contains(map[nx][ny])) {
                        tmp += 1;
                    }
                }
            }
            switch (tmp) {
                case 1:
                    ans += 1;
                    break;
                case 2:
                    ans += 10;
                    break;
                case 3:
                    ans += 100;
                    break;
                case 4:
                    ans += 1000;
            }
        }
        return ans;
    }

    public static List<Node1> checkCon2(int st, List<Node1> list) {
        int[][] arr = new int[n][n];
        int[][] ans = new int[n][n];

        List<Node1> rstList = new ArrayList<>();
        for (Node1 node1 : isSitted.values()) {
            arr[node1.x][node1.y] = -1;
        }
        for (Node1 node1 : list) {
            int x = node1.x;
            int y = node1.y;
            arr[x][y] = -2;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == -2) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isIn(nx, ny)) {
                            if (arr[nx][ny] != -1) {
                                ans[i][j] += 1;
                                max = Math.max(ans[i][j], max);
                            }
                        }
                    }
                }
            }
        }
        if (max == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == -2) {
                        rstList.add(new Node1(i, j));
                    }
                }
            }
            return rstList;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == max) {
                    rstList.add(new Node1(i, j));
                }
            }
        }
        return rstList;
    }

    public static List<Node1> checkCon1(int st, List<Integer> likeAndSitList) {
        int[][] arr = new int[n][n];
        for (Node1 node1 : isSitted.values()) {
            arr[node1.x][node1.y] = -1; //이미 차있음.
        }
        int max = 0;
        for (int std : likeAndSitList) {
            Node1 node1 = isSitted.get(std);
            for (int i = 0; i < 4; i++) {
                int nx = node1.x + dx[i];
                int ny = node1.y + dy[i];
                if (isIn(nx, ny)) {
                    if (arr[nx][ny] != -1) {
                        arr[nx][ny] += 1;
                        max = Math.max(arr[nx][ny], max);
                    }
                }
            }
        }
        List<Node1> nodeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == max) {
                    nodeList.add(new Node1(i, j));
                }
            }
        }
        return nodeList;
    }
}

class Node1 {
    int x;
    int y;

    Node1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}