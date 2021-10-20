import java.util.Locale;

public class Solution {

    public static void main(String[] args) throws Exception {
        Solution so = new Solution();
        System.out.println(so.solution("z-+.^."));
    }

    public String solution(String new_id) {
        String answer = "";
        String newId = new_id.toLowerCase(Locale.ROOT);
        newId = newId.replaceAll("[^a-z0-9_.-]","");
        newId = newId.replaceAll("[.]{2,}",".");
        if(newId.charAt(0) == '.'){
            newId = newId.substring(1,newId.length());
        }
        if(newId.length()>0 && newId.charAt(newId.length()-1) == '.'){
            newId = newId.substring(0,newId.length()-1);
        }
        if(newId.length() == 0){
            newId = "a";
        }
        if(newId.length() >= 16){
            newId = newId.substring(0,15);
            if(newId.length()>0 && newId.charAt(newId.length()-1) == '.'){
                newId = newId.substring(0,newId.length()-1);
            }
        }
        if(newId.length()<=2){
            while(newId.length() != 3){
                newId = newId + newId.substring(newId.length()-1, newId.length());
            }
        }
        answer = newId;
        return answer;
    }

}