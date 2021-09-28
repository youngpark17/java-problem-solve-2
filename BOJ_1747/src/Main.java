import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] visited;
    static boolean[] visited2;
    static List<Integer> numList;
    static int maxNum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        maxNum = 1_040_000;
        arr = new int[maxNum];
        visited =new boolean[maxNum];
        getNumber();
        visited2 = new boolean[numList.size()];
        numList = numList.stream().filter(num -> num>=n).collect(Collectors.toList());

        for(int k : numList){
            if(isPelindrome(k)){
                System.out.println(k);
                break;
            }
        }

    }
    static boolean isPelindrome(int numbers){
        char[] num = String.valueOf(numbers).toCharArray();
        int size = num.length;
        boolean flag = true;
        for (int i = 0; i < num.length/2; i++) {
            if(num[i] != num[size-1-i]){
                flag = false;
                break;
            }
        }
        return flag;
    }

    static void getNumber(){
        for (int i = 2; i <= Math.sqrt(maxNum); i++) {
            for (int j = i+i; j < maxNum; j+=i) {
                visited[j] = true;
            }
        }
        numList = new ArrayList<>();
        for (int i = 2; i < maxNum; i++) {
            if(visited[i] == false){
                numList.add(i);
            }
        }

    }
}
