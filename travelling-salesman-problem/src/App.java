import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws Exception {
        // Travelling Salesman Problem
        int[][] costMatrix = {
                //        1  2   3   4
                /* 1 */ { 0, 10, 15, 20 },
                /* 2 */ { 5, 0, 9, 10 },
                /* 3 */ { 6, 13, 0, 12 },
                /* 4 */ { 8, 8, 9, 0 }
        };

        // for(int[] row:adjencencyMatrix){
        // for(int ele:row){
        // System.out.print(ele+" ");
        // }
        // System.out.println();
        // }
        ArrayList<Integer> otherVertexes=new ArrayList<>();
        otherVertexes.add(2);
        otherVertexes.add(3);
        otherVertexes.add(4);
        int cost = travellingSalesmanProblem(costMatrix, 1, otherVertexes, 1);
        System.out.println(cost);
    }


    public static int travellingSalesmanProblem(int[][] costMatix,int startingVertex,ArrayList<Integer> otherElements,int currentVertex) {
        if(otherElements.isEmpty()){
            return costMatix[currentVertex-1][startingVertex-1];
        }
        int min=Integer.MAX_VALUE;
        for(int x=0;x<=otherElements.size();x++){
            int nextVertex = otherElements.get(x);
            int costToNextVertex = costMatix[currentVertex-1][nextVertex-1];
            ArrayList<Integer> updatedRemainingVertices = new ArrayList<>(otherElements);
            updatedRemainingVertices.remove(x);
            int subProblemCost = costToNextVertex + travellingSalesmanProblem(costMatix, startingVertex, updatedRemainingVertices, nextVertex);
            min = Math.min(min, subProblemCost);
        }
        return min;
    }   

}
