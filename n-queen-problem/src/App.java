public class App {
    public static void main(String[] args) throws Exception {
        n_queen(8);
    }

    public static void n_queen(int n){
        int[][] graph = new int[n][n];
        int numberOfWays=n_queen(graph, 0, n);
        System.out.printf("There are %d ways to place the queens on board of size %d x %d.",numberOfWays,n,n);
    }   
    
    public static int n_queen(int[][] graph,int i,int n){
        if(i==n){
            return 1;
        }
        int noOfWays=0;
        for(int j=0;j<graph[i].length;j++){
            if(isSafe(graph,i,j)){
                graph[i][j]=1;
                noOfWays+=n_queen(graph,i+1,n);
                graph[i][j]=0;
            }
        }
        return noOfWays;
    }

    public static boolean isSafe(int graph[][], int i, int j) {
        int n = graph.length;
        int j_left = j;
        int j_right = j;
        while (i >= 0) {
            if ((j_left >= 0 && graph[i][j_left] == 1) ||
                    (j_right < n && graph[i][j_right] == 1) ||
                    graph[i][j] == 1) {
                return false;
            }
            i--;
            j_left--;
            j_right++;
        }
        return true;
    }

}
