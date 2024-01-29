import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle {

    // The initial state of the puzzle.
    private Integer[] startState;

    // The final or goal state of the puzzle.
    private Integer[] finalState;

    // Constructor to initialize the puzzle with start and final states.
    public Puzzle(Integer[] startState, Integer[] finalState) {
        this.startState = startState;
        this.finalState = finalState;
    }

    private void swap(Integer[] state, int i, int j) {
        int temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    // Function to calculate the number of inversions in a given state of the puzzle
    // (ignoring 0.)
    private static int getInversionCount(Integer[] state) {
        int inversionCount = 0;

        for (int x = 0; x < state.length; x++) {
            for (int y = x + 1; y < state.length; y++) {
                if (state[x] > 0 && state[y] > 0 && state[x] < state[y]) {
                    inversionCount++;
                }
            }
        }

        return inversionCount;
    }

    // Function to check whether a puzzle is solvable
    /*
     * For an n puzzle to be solvable:
     * 1. n+1 should be a perfect square.
     * 2. Inversions%(sqrt(n+1)-1) should be equal to zero
     */
    public static boolean isSolvable(Integer[] initialState, Integer[] finalState) {

        int totalElementsInMatrix = initialState.length;
        int initialInversionCount = getInversionCount(initialState);
        int finalInversionCount = getInversionCount(finalState);
        double numberOfElements = Math.sqrt(totalElementsInMatrix);

        // Check if number if total elements is a perfect square (i.e. it is a valid matrix.)
        if(Math.floor((double)numberOfElements)==Math.ceil((double)numberOfElements)){
            
        // Check whether the initial and final inversion counts have the same parity
        // (both even or both odd)
            if (finalInversionCount % ((int)numberOfElements-1) == initialInversionCount % ((int)numberOfElements-1)) {
                return true;
            }
        }
        return false;
    }

    // Executes a move action on the current state of the puzzle and returns the new
    // state.
    private Integer[] executeMove(Integer[] currentState, String action) {
        Integer[] newState = Arrays.copyOf(currentState,currentState.length);
        int blankIndex = getBlankIndex(currentState);

        // Here we assume that `currentState.length` will be a perfect square as execute will get triggered only if puzzle is solvable.
        int numberOfColumns = (int)Math.sqrt(currentState.length);

        // Swapping the blank tile based on the action taken.
        if (action.equals("UP")) {
            this.swap(newState, blankIndex, blankIndex - numberOfColumns);
        } else if (action.equals("DOWN")) {
            this.swap(newState, blankIndex, blankIndex + numberOfColumns);
        } else if (action.equals("LEFT")) {
            this.swap(newState, blankIndex, blankIndex - 1);
        } else if (action.equals("RIGHT")) {
            this.swap(newState, blankIndex, blankIndex + 1);
        }
        return newState;
    }

    // Finds the index of the blank (0) tile in the current state.
    private int getBlankIndex(Integer[] state) {
        for (int x = 0; x < state.length; x++) {
            if (state[x] == 0) {
                return x;
            }
        }
        return -1;
    }

    // Generates all possible actions (moves) from the current state.
    private ArrayList<String> getAllPossibleActions(Integer[] state) {
        ArrayList<String> actions = new ArrayList<>();

        int blankIndex = this.getBlankIndex(state);

        // Here we assume that `currentState.length` will be a perfect square as execute will get triggered only if puzzle is solvable.
        int numberOfColumns = (int)Math.sqrt(state.length);

        // Checking the position of the blank tile to determine possible moves.
        if (blankIndex > (numberOfColumns-1))
            actions.add("UP");
        if (blankIndex < (state.length-numberOfColumns)-1)
            actions.add("DOWN");
        if (blankIndex % (numberOfColumns-1) != 1)
            actions.add("LEFT");
        if (blankIndex + 1 % (numberOfColumns-1) != 0)
            actions.add("RIGHT");

        return actions;
    }

    // Checks if the current state is the final state.
    private boolean isFinalState(Integer[] currentState) {
        return Arrays.equals(currentState, this.finalState);
    }

    // Checks if a given state has already been explored.
    private boolean containsState(ArrayList<Node> explored, Integer[] toCheck) {
        for (Node node : explored) {
            if (Arrays.equals(node.state, toCheck)) {
                return true;
            }
        }
        return false;
    }

    // Helper function to trace the path for the solution node.
    private ArrayList<Node> buildSolution(Node finalNode) {
        ArrayList<Node> path = new ArrayList<>();
        Node temp = finalNode;
        while (temp != null) {
            path.add(temp);
            temp = temp.parent;
        }
        Collections.reverse(path); // Reverse the path to get it from start to finish.
        return path;
    }

    // Solves the puzzle and returns the path from the initial to the final state.
    public ArrayList<Node> solvePuzzle(boolean debug) {
        if (isSolvable(this.startState, this.finalState)) {
            Node initialNode = new Node(startState, null, "Initial", 0);

            // If initial node is itself the solution node.
            if (this.isFinalState(initialNode.state))
                return new ArrayList<Node>(Arrays.asList(initialNode));

            Queue<Node> frontier = new LinkedList<>();
            ArrayList<Node> exploredNodes = new ArrayList<>();
            frontier.add(initialNode);

            // This generates a search tree and traverses it in breadth first way.
            while (!frontier.isEmpty()) {

                Node currentNode = frontier.poll();
                exploredNodes.add(currentNode);

                if (debug) {
                    System.out.println(
                            "\n-------------------------------------------------------------------\nCurrent State: "
                                    + Node.getMatrix(currentNode.state) + "Level: " + currentNode.level
                                    + "\n-------------------------------------------------------------------\n");
                }

                // Getting all possible actions from the current state and exploring them.
                ArrayList<String> possibleActions = this.getAllPossibleActions(currentNode.state);
                for (String action : possibleActions) {
                    Integer[] newMove = this.executeMove(currentNode.state, action);

                    // Check if the newly generated state has already been explored.
                    if (!containsState(exploredNodes, newMove)) {
                        Node newNode = new Node(newMove, currentNode, action, currentNode.level + 1);
                        // Check if the required state is the final state.
                        if (this.isFinalState(newNode.state)) {
                            return buildSolution(newNode);
                        }

                        frontier.add(newNode); // Adding the new state to the frontier (Queue) for further exploration.
                    }
                }
            }
            return null;
        } else {
            System.out.println("There is no solution for this combination of start and end state.");
            return null;
        }

    }

}
