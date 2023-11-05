import java.util.HashMap;

public class MinChangeExample{
    public static void main(String[] args) {
        int amount=132;
        int[] numbers={1,2,3};
        System.out.println("Min number of change required: "+minChange(amount, numbers));
    }

    public static int minChange(int amount,int[] numbers){
        return minChange(amount, numbers,new HashMap<>());
    }

    public static int minChange(int amount,int[] numbers,HashMap<Integer,Integer> memo){
        if(amount<1){
            if(amount==0){
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        if(memo.containsKey(amount)){
            return memo.get(amount);
        }

        int min = Integer.MAX_VALUE;
        for(int ele:numbers){
            int val=minChange(amount-ele, numbers,memo);
            if(val<min){
                min=val;
            }
        }
        memo.put(amount,min+1);
        return memo.get(amount);
    }
}