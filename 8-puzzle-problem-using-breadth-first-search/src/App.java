public class App {
    public static void main(String[] args) throws Exception {
        int[] start = {1,2,3,4,5,6,7,8,9};
        Node startState = new Node(start,null,"Initial",0);
        System.out.println(startState);
    }
}
