import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Integer[] startState = { 1, 2, 3, 4, 5, 6, 0, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        Integer[] goalState = { 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 15 };
        Puzzle puzzle = new Puzzle(startState, goalState);
        ArrayList<Node> path = puzzle.solvePuzzle(true);
        if (path != null) {
            for (Node node : path) {
                System.out.println(
                        "\n############################################################################\nCurrent State:\n"
                                + Node.getMatrix(node.state) + "\nLevel: " + node.level + "\nAction: " + node.action
                                + "\n############################################################################");
            }
        }
    }
}
