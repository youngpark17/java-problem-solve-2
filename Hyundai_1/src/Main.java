import java.util.*;

public class Main {
    Map<String, Integer> mapCount; //이름, 카운터
    Map<String, List<Integer>> mapRoomN; //이름 방호수
    Map<Integer,List<String>> mapName; //방호수, 이름
    public static void main(String[] args) {
        Main m = new Main();
        //String[] rooms = {"[403]James", "[404]Azad,Louis,Andy","[101]Azad,Guard"};
        //String[] rooms = {"[101]Azad,Guard", "[202]Guard","[303]Guard,Dzaz"};
        String[] rooms = {"[1234]None,Of,People,Here", "[5678]Wow"};
        String[] answer = m.solution(rooms,1234);
        for(String aa : answer){
            System.out.print(aa+" ");
        }
    }
    public String[] solution(String[] rooms, int target){
        mapCount = new HashMap<>();
        mapRoomN = new HashMap<>();
        mapName = new HashMap<>();
        for(String room : rooms){
            String[] arr = room.split("\\]");
            int roomN = Integer.parseInt(arr[0].substring(1));
            String[] nameArr = arr[1].split(",");
            for(String name : nameArr){
                mapCount.put(name,mapCount.getOrDefault(name,0)+1);
                List<String> tmp2 = Arrays.asList(nameArr);
                mapName.put(roomN,tmp2);
                if(mapRoomN.containsKey(name)){
                    mapRoomN.get(name).add(roomN);
                }
                else{
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(roomN);
                    mapRoomN.put(name,tmp);
                }
            }
        }
        List<String> names = mapName.get(target);
        for(String name : names){ //방 차지한건 계산에서 제외
            mapCount.remove(name);
            mapRoomN.remove(name);
        }
        List<Node> nodeList = new ArrayList<>();
        Set<String> set = mapCount.keySet();
        for(String k : set){
            int pro1 = mapCount.get(k);
            int pro2 = mapRoomN.get(k).stream().map(v -> Math.abs(v-target)).min(Comparator.naturalOrder()).get();
            Node node = new Node(k,pro1,pro2);
            nodeList.add(node);
        }
        Collections.sort(nodeList);
        int size = nodeList.size();
        String[] answer = new String[size];
        for (int i = 0; i < size; i++) {
            answer[i] = nodeList.get(i).name;
        }


        // 해당방에 지정자리가 x
        // 직원의 자리가 제일 적은
        // 거리 -> 이름 사전순
        // 일단 다 넣고 삭제하자

        return answer;
    }
}

class Node implements Comparable<Node>{
    String name;
    int pro1; //지정자리수 -> 오름차순
    int pro2; //방에 가까운 정도 작은수록 좋음 -> 오름차순

    Node(String name, int pro1, int pro2){
        this.name=name;
        this.pro1=pro1;
        this.pro2=pro2;
    }

    @Override
    public int compareTo(Node o) {
        if(pro1 != o.pro1){
            return pro1-o.pro1;
        }
        if(pro2 != o.pro2){
            return pro2-o.pro2;
        }
        return name.compareTo(o.name);
    }
}

