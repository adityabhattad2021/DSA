import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] adjacencyMatrix = {
            {0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0} 
        };
        char[] frequencies = {'A','B','C','D'};
        List<HashMap<Integer, Character>> options = graphColouring(adjacencyMatrix, frequencies);
        if(options.size()==0){
            System.out.println("No possible way found to color the graphs with the given colors (frequiencies in the context of the question).");
        }else{
            System.out.println("Here are all the following options the graph can be colored: ");
            for(HashMap<Integer, Character> option:options){
                System.out.println(option);
            }
        }
    }

    public static List<HashMap<Integer,Character>> graphColouring(int[][] adjacencyMatrix,char[] frequencies){
        // Using a hashmap, because it seems a good way to visualize perticular graph getting colored by a perticular color.
        List<HashMap<Integer,Character>> options = new ArrayList<>();
        graphColouring(adjacencyMatrix,frequencies,0,new HashMap<>(),options);
        return options;
    }

    public static void graphColouring(int[][] adjacencyMatrix,char[] frequencies,int current,HashMap<Integer,Character> map,List<HashMap<Integer,Character>> options){
        // If we are at the 1+last index, this means all the index are colored add the current sequence of color in the hashmap and return.
        if(current==adjacencyMatrix.length){
            // If array is already of length 0.
            if(adjacencyMatrix.length==0){
                return;
            }
            options.add(new HashMap<>(map));
            return;
        }

        List<Character> availableColors = new ArrayList<>();
        for(int x=0;x<frequencies.length;x++){
            // (First refer the canColor function) for the current node add all the possible ways it can be colored in a list.
            if(canColor(adjacencyMatrix,frequencies,map,current,frequencies[x])){
                availableColors.add(frequencies[x]);
            }
        }

        // Iterate through the list of possible colors, add the current combination of color and node to the map and move to next node and call the function recursively(repeat the same thing on the next node).
        for(Character color:availableColors){
            map.put(current,color);
            graphColouring(adjacencyMatrix,frequencies,current+1,map,options);
        }
    }

    // Check if any connected node to the current node does not have the same color.
    public static boolean canColor(int[][] adjacencyMatrix,char[] frequencies,HashMap<Integer,Character> map,int currentIndex,char currentColor){
        for(int x=0;x<adjacencyMatrix[currentIndex].length;x++){
            if(adjacencyMatrix[currentIndex][x]==1 && map.containsKey(x) && map.get(x)==currentColor){
                // This is where a perticular node is getting killed if further coloring is not possible.
                return false;
            }
        }
        return true;
    }
}
