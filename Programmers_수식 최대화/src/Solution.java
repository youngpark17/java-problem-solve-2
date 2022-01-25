import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Solution {
    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution("100-200*300-500+20"));
    }

    public long solution(String expression) {
        long answer = 0;
        long[] numbers = Arrays.stream(expression.split("[\\-|\\*|\\+]")).mapToLong(Long::parseLong).toArray();
        String[] ops = Arrays.stream(expression.split("\\d+")).filter(a -> !"".equals(a)).toArray(String[]::new);

        for (int i = 1; i <= 6; i++) {
            answer = Math.max(answer,calc(numbers.clone(),ops.clone(),i));
        }


        return answer;
    }
    public long calc(long[] numbers, String[] ops, int k){
        Deque<Long> deque = Arrays.stream(numbers).boxed().collect(Collectors.toCollection(LinkedList::new));
        if(k==1){ //* > + > -
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            return Math.abs(deque.pollFirst());
        }
        else if(k==2){ // * > - >+
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");

            return Math.abs(deque.pollFirst());
        }
        else if(k==3){ // + > * > -
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            return Math.abs(deque.pollFirst());
        }
        else if(k==4){ // + > - > *
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            return Math.abs(deque.pollFirst());
        }
        else if(k==5){ // - * +
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");
            return Math.abs(deque.pollFirst());
        }
        else{
            deque = calcSubtract(deque, ops);
            ops = removeOps(ops, "-");
            deque = calcAdd(deque, ops);
            ops = removeOps(ops, "+");
            deque = calcMultify(deque, ops);
            ops = removeOps(ops, "*");
            return Math.abs(deque.pollFirst());
        }

    }

    private Deque calcSubtract(Deque<Long> deque, String[] ops) {
        Deque<Long> outQue = new LinkedList();
        if(deque.size() == 1){
            return deque;
        }
        outQue.add(deque.pollFirst());
        for(String op : ops){
            if("-".equals(op)){
                long inValue = outQue.pollLast().longValue();
                outQue.add(inValue-deque.pollFirst());
            }
            else{
                outQue.addLast(deque.pollFirst());
            }
        }
        return outQue;
    }

    private Deque calcAdd(Deque<Long> deque, String[] ops) {
        Deque<Long> outQue = new LinkedList();
        if(deque.size() == 1){
            return deque;
        }
        outQue.add(deque.pollFirst());
        for(String op : ops){
            if("+".equals(op)){
                long inValue = outQue.pollLast().longValue();
                outQue.add(inValue+deque.pollFirst());
            }
            else{
                outQue.addLast(deque.pollFirst());
            }
        }
        return outQue;
    }

    private Deque calcMultify(Deque<Long> deque, String[] ops) {
        Deque<Long> outQue = new LinkedList();
        if(deque.size() == 1){
            return deque;
        }
        outQue.add(deque.pollFirst());
        for(String op : ops){
            if("*".equals(op)){
                long inValue = outQue.pollLast().longValue();
                outQue.add(inValue*deque.pollFirst());
            }
            else{
                outQue.addLast(deque.pollFirst());
            }
        }
        return outQue;
    }


    private String[] removeOps(String[] ops, String op){
        return Arrays.stream(ops).filter(a -> !op.equals(a)).toArray(String[]::new);
    }


}

