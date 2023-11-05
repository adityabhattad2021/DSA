import java.util.HashMap;

public class SumPossibleExample {

    public static void main(String[] args) {
        int sum = 4;
        int[] numbers = {1,2,3};
        System.out.println(sumPossible(sum,numbers));
        sum = 15;
        int[] numbers1 = {4,6,10};
        System.out.println(sumPossible(sum,numbers1));
    }

    public static boolean sumPossible(int sum,int[] numbers){
        return sumPossible(sum, numbers, new HashMap<>());
    }

    public static boolean sumPossible(int sum,int[] numbers,HashMap<Integer,Boolean> memo){
        if(sum<1){
            if(sum==0){
                return true;
            }
            return false;
        }

        if(memo.containsKey(sum)){
            return memo.get(sum);
        }
        
        boolean flag=false;
        for(int ele:numbers){
            boolean result = sumPossible(sum-ele, numbers, memo);
            if(result){
                flag=true;
                break;
            }
        }
        memo.put(sum,flag);
        return memo.get(sum);
    }
}