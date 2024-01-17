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

    // Function to calculate the number of inversions in a given state of the puzzle (ignoring 0.)
private int getInversionCount(Integer[] state) {
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
 private boolean isSolvable(Integer[] initialState, Integer[] finalState) {
    int initialInversionCount = this.getInversionCount(initialState);
    
    int finalInversionCount = this.getInversionCount(finalState);
    
    // Check whether the initial and final inversion counts have the same parity (both even or both odd)
    if (finalInversionCount % 2 == initialInversionCount % 2) {
        return true;
    } else {
        return false;
    }
 }
 

    // Executes a move action on the current state of the puzzle and returns the new
    // state.
    private Integer[] executeMove(Integer[] currentState, String action) {
        Integer[] newState = Arrays.copyOf(currentState, 9);
        int blankIndex = getBlankIndex(currentState);

        // Swapping the blank tile based on the action taken.
        if (action.equals("UP")) {
            this.swap(newState, blankIndex, blankIndex - 3);
        } else if (action.equals("DOWN")) {
            this.swap(newState, blankIndex, blankIndex + 3);
        } else if (action.equals("LEFT")) {
            this.swap(newState, blankIndex, blankIndex - 1);
        } else if (action.equals("RIGHT")) {
            this.swap(newState, blankIndex, blankIndex + 1);
        }
        return newState;
    }

    // Finds the index of the blank (0) tile in the current state.
    private int getBlankIndex(Integer[] state) {
        for (int x = 0; x < 9; x++) {
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

        // Checking the position of the blank tile to determine possible moves.
        if (blankIndex > 2)
            actions.add("UP");
        if (blankIndex < 6)
            actions.add("DOWN");
        if ((blankIndex + 1) % 3 != 1)
            actions.add("LEFT");
        if ((blankIndex + 1) % 3 != 0)
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

        // Return null if no solution is found.
        return null;
    }

}
