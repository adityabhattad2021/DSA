public class App {
    public static void main(String[] args) throws Exception {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(23).insert(52).insert(35).insert(5).insert(81);
        bst.setTraversalType(bst.getPreOrderTraversal());
        bst.traverse();
    }
}
