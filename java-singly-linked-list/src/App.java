public class App {
    public static void main(String[] args) throws Exception {
        String name="Aitya";
        SinglyLinkedList<Character> sll=new SinglyLinkedList<>(name.charAt(0));
        for(int x=1;x<name.length();x++){
            sll.addNode(name.charAt(x));
        }
        // sll.addNodeAtStart('M');
        sll.traverseList();
        sll.getLength();

        sll.addAtGivenPos('d', 2);
        sll.traverseList();
        sll.addAtGivenPos('B', 7);
        sll.traverseList();
    }
}