import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class MaxNonAdjacentSum {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,1,9,21,15,12,5,8);
        System.out.println("The max sum that can be obtained by adding non adjacent elements of the given array is: "+maxNonAdjacentSum(numbers)+".");
    }

    public static int maxNonAdjacentSum(List<Integer> numbers){
        return maxNonAdjacentSum(numbers,0,new HashMap<>());
    }

    public static int maxNonAdjacentSum(List<Integer> numbers,int index,HashMap<Integer,Integer> memo){
        if(index>=numbers.size()){
            return 0;
        }

        if(memo.containsKey(index)){
            return memo.get(index);
        }

        int case1 = numbers.get(index)+maxNonAdjacentSum(numbers,index+2,memo);
        int case2 = maxNonAdjacentSum(numbers,index+1,memo);

        memo.put(index,Math.max(case1,case2));
        return memo.get(index);   
    }
    
    
}