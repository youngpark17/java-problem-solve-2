import java.util.*;

public class Solution {
    Map<String,String> idNameMap;
    Map<Integer, String> inOutMap;

    public static void main(String[] args){
        Solution so = new Solution();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] ans  =so.solution(record);
        for(String kk : ans){
            System.out.println(kk);
        }
    }

    public String[] solution(String[] record) {
        String[] answer = {};
        idNameMap = new HashMap<>();
        inOutMap = new LinkedHashMap<>();
        int cnt = 10;
        for(String re : record){
            String[] tmp = re.split(" ");
            if("Enter".equals(tmp[0])){
                enter(cnt,tmp[1],tmp[2]);
            }
            else if("Leave".equals(tmp[0])){
                leave(cnt,tmp[1]);
            }
            else{
                change(tmp[1],tmp[2]);
            }
            cnt+=10;
        }
        List<String> list = new ArrayList<>();
        for(Map.Entry<Integer,String> m : inOutMap.entrySet()){
            if(m.getKey()%2 == 0){
                list.add(String.format("%s님이 들어왔습니다.",idNameMap.get(m.getValue())));
            }
            else{
                list.add(String.format("%s님이 나갔습니다.",idNameMap.get(m.getValue())));
            }
        }

        return list.toArray(String[]::new);
    }

    public void change(String id, String name){
        idNameMap.put(id,name);
    }

    public void enter(int k,String id, String name){
        idNameMap.put(id,name);
        inOutMap.put(k,id);
    }
    public void leave(int k,String id){
        inOutMap.put(k+1,id);
    }
}