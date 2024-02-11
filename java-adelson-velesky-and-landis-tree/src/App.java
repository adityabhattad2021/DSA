public class App {
    public static void main(String[] args) throws Exception {
        IAVLTree<Integer> avlTree = new AVLTree<Integer>();
        avlTree.insert(23).insert(52).insert(35).insert(5).insert(81).insert(6).insert(21).insert(45).insert(4).insert(3);
        avlTree.traverse();
    }
}
