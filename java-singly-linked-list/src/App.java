public class App {
    public static void main(String[] args) throws Exception {
        String name="Aitya";
        SinglyLinkedList<Character> sll=new SinglyLinkedList<>(name.charAt(0));
        for(int x=1;x<name.length();x++){
            sll.addNode(name.charAt(x));
        }
        // sll.addNodeAtStart('M');
        sll.traverseList();
        
        sll.addAtGivenPos('d', 2);
        sll.printLength();
        sll.traverseList();
        sll.addAtGivenPos('B', 7);
        // sll.addAtGivenPos('B', 7);
        sll.traverseList();
        Character middle=sll.findMiddleElement();
        System.out.println("Middle character is: "+middle);
        // sll.searchForElement('X');
        // sll.reverseTheList();
        // sll.traverseList();
    }

}
