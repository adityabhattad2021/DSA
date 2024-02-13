import java.util.Arrays;

public class ManhattenDistance implements IHeuristicFunction{
    public int calculateHeuristicValue(Integer[] currentState, Integer[] finalState) {
        int manhattanDistance = 0;
        int sideLength = (int) Math.sqrt(currentState.length);
        for (int i = 0; i < currentState.length; i++) {
            int currentVal = currentState[i];
            if (currentVal != 0) { // Skip the blank tile
                int goalIndex = Arrays.asList(finalState).indexOf(currentVal);
                int currentRow = i / sideLength, currentCol = i % sideLength;
                int goalRow = goalIndex / sideLength, goalCol = goalIndex % sideLength;
                manhattanDistance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
            }
        }
        return manhattanDistance;
    }
}
