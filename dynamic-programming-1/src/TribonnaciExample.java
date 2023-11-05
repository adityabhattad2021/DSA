import java.util.HashMap;


public class TribonnaciExample{
    public static void main(String[] args) {
        for(int x=1;x<=20;x++){
            System.out.println("trib("+x + ") = " + trib(x));
        }
    }

    public static int trib(int n){
        return trib(n,new HashMap<Integer,Integer>());
    }

    public static int trib(int n,HashMap<Integer,Integer> memo){
        if(n<4){
            if(n==1){
                return 0;
            }else{
                return 1;
            }
        }
        
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        memo.put(n, trib(n-1,memo)+trib(n-2,memo)+trib(n-3,memo));
        return memo.get(n);
    }
}