import java.util.HashMap;


public class FibExample{
    public static void main(String[] args) {
        for(int x=0;x<30;x++){
            System.out.println(fib(x));
        }
    }

    public static int fib(int n, java.util.HashMap<Integer, Integer> memo){
        if(n<2){
            return n;
        }

        if(memo.containsKey(n)){
            return memo.get(n);
        }

        memo.put(n, (fib(n-1,memo)+fib(n-2,memo)));
        return memo.get(n);
    }

    public static int fib(int n){
        return fib(n,new HashMap<Integer,Integer>());
    }
}