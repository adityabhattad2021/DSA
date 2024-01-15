public class Node{
    int[] state;
    Node parent;
    String action;
    int level;


    public Node(int[] state, Node parent, String action, int level) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.level = level;
    }

    public String getMatrix(int[] state){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for(int x=0;x<3;x++){
            sb.append("     ");
            for(int y=0;y<3;y++){
                sb.append(state[y+3*x]+" ");
            }
            sb.append("\n");
        }   
        return sb.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb  = new StringBuilder();
        sb.append("\n=====================================\n");
        sb.append("Current State:");
        sb.append(getMatrix(this.state));
        sb.append("Parent State:");
        if (this.parent != null) {
            sb.append(getMatrix(this.parent.state));
        } else {
            sb.append("None\n");
        }
        sb.append("Action: "+this.action+"\n");
        sb.append("Level: "+this.level+"\n");
        sb.append("\n=====================================\n");
        return sb.toString();
    }
   
}