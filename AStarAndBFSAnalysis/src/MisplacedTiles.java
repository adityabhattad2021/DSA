public class MisplacedTiles implements IHeuristicFunction{
    public int calculateHeuristicValue(Integer[] currentState, Integer[] finalState) {
        int misplacedTiles = 0;
        for (int i = 0; i < currentState.length; i++) {
            if (currentState[i] != finalState[i]) {
                misplacedTiles++;
            }
        }
        return misplacedTiles;
    }
}
