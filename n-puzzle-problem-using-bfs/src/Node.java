public class Node {
    // Properties of the Node class representing the state of the puzzle,
    // the parent node in the path(the action that led to this state), and the level in the search tree.
    Integer[] state;
    Node parent;
    String action;
    int level;

    /**
     * Constructor for Node class.
     * @param state The current state of the puzzle.
     * @param parent The parent node from which this node was reached.
     * @param action The action taken to reach this state from the parent node.
     * @param level The depth level of this node in the search tree.
     */
    public Node(Integer[] state, Node parent, String action, int level) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.level = level;
    }

    /**
     * Converts the puzzle state into a formatted 3x3 matrix string.
     * @param state The state of the puzzle to be formatted.
     * @return A string representation of the state in a 3x3 matrix format.
     */
    public static String getMatrix(Integer[] state) {
        StringBuilder sb = new StringBuilder();
        Integer numberOfColumns = (int) Math.sqrt(state.length);
        sb.append("\n");
        // Loop through the state array and format it into a 3x3 grid.
        for (int x = 0; x < numberOfColumns; x++) {
            sb.append("     ");
            for (int y = 0; y < numberOfColumns; y++) {
                sb.append(String.format("%02d", state[y + numberOfColumns * x]) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=====================================\n");
        sb.append("Current State:");
        sb.append(getMatrix(this.state));
        sb.append("Parent State:");
        // If the node has a parent, include the parent's state. Otherwise, indicate 'None'.
        if (this.parent != null) {
            sb.append(getMatrix(this.parent.state));
        } else {
            sb.append("None\n");
        }
        sb.append("Action: " + this.action + "\n");
        sb.append("Level: " + this.level + "\n");
        sb.append("\n=====================================\n");
        return sb.toString();
    }
}
