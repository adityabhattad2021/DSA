import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


class Key{
    public int amount;
    public int index;

    public Key(int index,int amount){
        this.amount=amount;
        this.index=index;
    }

}

public class CountChangeExample {

    public static void main(String[] args) {
        int amount = 12;
        List<Integer> coins= Arrays.asList(1,2,3);
        System.out.println("The number of ways to make amount using given coins are: "+countingCoins(amount, coins)+".");
    }

    public static int countingCoins(int amount,List<Integer> coins){
        return countingCoins(amount,coins,0,new HashMap<>());
    }

    public static int countingCoins(int amount,List<Integer> coins,int index,HashMap<Key,Integer> memo){

        if(amount==0){
            return 1;
        }

        if(index>=coins.size()){
            return 0;
        }
        Key key = new Key(amount,index);
        if(memo.containsKey(key)){
            return memo.get(key);
        }

        int total = 0;
        for(int x=0;x*coins.get(index)<=amount;x++){
            total+=countingCoins(amount-(coins.get(index)*x), coins, index+1, memo);
        }
        memo.put(key,total);
        return memo.get(key);
    }

    
}