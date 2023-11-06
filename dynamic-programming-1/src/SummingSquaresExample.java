import java.util.HashMap;

public class SummingSquaresExample {

    public static void main(String[] args) {
        int number=10;
        System.out.println(summingSquares(number));
    }

    public static int summingSquares(int number){
        return summingSquares(number,new HashMap<>());
    }

    public static int summingSquares(int number,HashMap<Integer,Integer> memo){

        if(number==1){
            return 1;
        }

        if(number<1){
            return 0;
        }

        if(memo.containsKey(number)){
            return memo.get(number);
        }

        int min = Integer.MAX_VALUE;
        for(int x=1;x*x<number;x++){
            int value = summingSquares(number-(x*x),memo)+1;
            if(value<min){
                min=value;
            }
        }
        memo.put(number,min);
        return memo.get(number);

    }
}