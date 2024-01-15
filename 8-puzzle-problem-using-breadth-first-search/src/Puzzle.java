import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle {

    private Integer[] startState;
    private Integer[] finalState;

    public Puzzle(Integer[] startState, Integer[] finalState) {
        this.startState = startState;
        this.finalState = finalState;
    }

    private void swap(Integer[] state, int i, int j) {
        int temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    private Integer[] executeMove(Integer[] currentState, String action) {
        Integer[] newState = Arrays.copyOf(currentState, 9);
        int blankIndex = getBlankIndex(currentState);
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

    private int getBlankIndex(Integer[] state) {
        for (int x = 0; x < 9; x++) {
            if (state[x] == 0) {
                return x;
            }
        }
        return -1;
    }

    private ArrayList<String> getAllPossibleActions(Integer[] state) {
        ArrayList<String> actions = new ArrayList<>();

        int blankIndex = this.getBlankIndex(state);

        if (blankIndex > 2) {
            actions.add("UP");
        }
        if (blankIndex < 6) {
            actions.add("DOWN");
        }
        if (blankIndex % 3 == 0) {
            actions.add("LEFT");
        }
        if (blankIndex % 3 > 0) {
            actions.add("RIGHT");
        }

        return actions;
    }

    private boolean isFinalState(int[] currentState) {
        return Arrays.equals(startState, this.finalState);
    }

    private boolean containsArray(ArrayList<Integer[]> states,Integer[] toCheck){
        for(Integer[] ele:states){
            if(Arrays.equals(ele, toCheck)){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Node> solvePuzzle() {
        Queue<Integer[]> frontier = new LinkedList<>();
        frontier.add(startState);
        ArrayList<Integer[]> exploredStates = new ArrayList<>();

        while(!frontier.isEmpty()){
            
            Integer[] currentState  = frontier.poll();
            ArrayList<String> possibleActions = getAllPossibleActions(currentState);
            for(String action:possibleActions){
                Integer[] newMove = executeMove(currentState, action);
                if(!containsArray(exploredStates, newMove)){
                    frontier.add(newMove);
                    
                }
            }

        }

        return null;
    }

}
