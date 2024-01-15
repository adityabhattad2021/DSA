import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Integer[] startState = { 1, 2, 3, 4, 5, 6, 0, 7, 8 };
        Integer[] goalState = { 6, 1, 3, 4, 5, 2, 7, 0, 8 };
        Puzzle puzzle = new Puzzle(startState, goalState);
        ArrayList<Node> path = puzzle.solvePuzzle(true);
        for (Node node : path) {
            System.out.println(
                    "\n############################################################################\nCurrent State:\n"
                            + Node.getMatrix(node.state) + "\nLevel: " + node.level + "\nAction: " + node.action
                            + "\n############################################################################");
        }
    }
}
