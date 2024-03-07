import java.util.PriorityQueue;

public class AStar8Queen {
    static final int N = 8;

    static class Node implements Comparable<Node> {
        int[] queens;
        int g; // Cost from start node to this node
        int h; // Heuristic value of this node (number of attacking pairs)
        int f; // Total cost (g + h)

        public Node(int[] queens, int g, int h) {
            this.queens = queens.clone();
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(Node other) {
            return this.f - other.f;
        }
    }


    public static int calculateHeuristic(int[] queens) {
        int attackingPairs = 0;
        for (int i = 0; i < N; i++) {
            if (queens[i] == -1) continue; 
            for (int j = i + 1; j < N; j++) {
                if (queens[j] == -1) continue; 
             
                if (queens[i] == queens[j] || Math.abs(i - j) == Math.abs(queens[i] - queens[j])) {
                    attackingPairs++;
                }
            }
        }
        return attackingPairs;
    }

    public static void solve(boolean debug) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] startState = new int[N];
        for(int x=0;x<startState.length;x++) startState[x]=-1;
        int startHeuristic = calculateHeuristic(startState);
        Node startNode = new Node(startState,0,startHeuristic);
        pq.add(startNode);

        while(!pq.isEmpty()){

            Node currentState = pq.poll();

            if(currentState.h==0 && !containsUnplacedQueens(currentState.queens)){
                System.out.println("Solution found: ");
                printState(currentState.queens);
                return;
            }

            for(int x=0;x<N;x++){
                if(currentState.queens[x]==-1){
                    int[] newPositions = currentState.queens.clone();
                    for(int y=0;y<N;y++){
                        newPositions[x]=y;
                        Node newNode = new Node(newPositions,currentState.g+1,calculateHeuristic(newPositions));
                        pq.add(newNode);
                    }
                    break;
                }
            }

            if(debug){
                System.out.println("-----------------------------------");
                printState(currentState.queens);
            }
            
        }

        System.out.println("Solution not found.");
    }

    private static boolean containsUnplacedQueens(int[] queens) {
        for (int queen : queens) {
            if (queen == -1) return true;
        }
        return false;
    }

   
    public static void printState(int[] state) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve(true);
    }
}
