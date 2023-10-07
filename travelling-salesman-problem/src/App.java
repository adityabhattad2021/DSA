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
        travellingSalesmanProblem(costMatrix);
    }

    public static void travellingSalesmanProblem(int[][] costMatix) {
        int[] selected = {1,0,0,0,0};
        int startingCost=0;
        for(int x=1;x<=4;x++){
            int[] costforStage1={0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
            for(int y=1;y<=4;y++){
                if(x!=y){
                    costforStage1[y]=costMatix[x][y];
                }
            }
            int[] costforStage2={0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
            for(int y=1;y<=4;y++){
                for(int z=1;z<=4;z++){
                    if(y!=1 && y!=z){
                        int minfromStage1 = Integer.MAX_VALUE;
                         
                    }
                }
            }
        }
    }   
}
