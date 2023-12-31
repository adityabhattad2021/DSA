import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


class Key {
    
    public int start;
    public List<Integer> rem;

    public Key(int start,List<Integer> rem){
        this.start=start;
        this.rem=rem;
    }
    
}

public class App {
    public static void main(String[] args) throws Exception {
        int[][] adjacencyMatrix = {
        //   A   B   C   D
            {0,  10, 15, 20}, // A
            {5,  0,  9,  10}, // B
            {6,  13, 0,  12}, // C
            {8,  8,  9,  0}   // D
        };
        System.out.println(travelingSalesmanSolution(adjacencyMatrix,0));
    }

    public static int travelingSalesmanSolution(int[][] matrix,int startingVertex){
        List<Integer> rem = new ArrayList<>(Arrays.asList(0,1,2,3));
        rem.remove(startingVertex);
        return travelingSalesmanSolution(
            matrix,
            startingVertex,
            startingVertex,
            rem,
            new HashMap<>()
        );
    }

    public static int travelingSalesmanSolution(
        int[][] matrix,
        int original,
        int start,
        List<Integer> rem,
        HashMap<Key,Integer> memo
    ){
        if(rem.isEmpty()){
            return matrix[start][original];
        }

        Key key = new Key(start,rem);
        if(memo.containsKey(key)){
            return memo.get(key);
        }

        int min = Integer.MAX_VALUE;
        for(int ele:rem){
            List<Integer> newList = new ArrayList<>(rem);
            newList.remove(Integer.valueOf(ele));
            int val = matrix[start][ele]+travelingSalesmanSolution(matrix, original, ele,newList, memo);
            if(val<min){
                min=val;
            }
        }
        memo.put(key,min);
        return memo.get(key);
    }
}
