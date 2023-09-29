public class App {
    public static void main(String[] args) throws Exception {
        String name="Rahul Verma is in CSE-A-2025";
        SinglyLinkedList<Character> sll=new SinglyLinkedList<>(name.charAt(0));
        for(int x=1;x<name.length();x++){
            sll.addNode(name.charAt(x));
        }
        sll.traverseList();
    }
}
