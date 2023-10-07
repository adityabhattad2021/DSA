import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        // For generic linked list operations, but some operations like sorting, etc are easier to implement with int data type, therefore, I created an new IntegerSinglyLinkedList.
        // String name="Aitya";
        // SinglyLinkedList<Character> sll=new SinglyLinkedList<>(name.charAt(0));
        // for(int x=1;x<name.length();x++){
        //     sll.addNode(name.charAt(x));
        // }
        // // sll.addNodeAtStart('M');
        // // sll.traverseList();
        
        // sll.addAtGivenPos('d', 2);
        // // sll.printLength();
        // // sll.traverseList();
        // sll.addAtGivenPos('B', 7);

        // // sll.traverseList();
        // // Character middle=sll.findMiddleElement();
        // // System.out.println("Middle character is: "+middle);
        // // Character nthFromEnd=sll.findNthNodeFromEnd(6);
        // // System.out.println("The nth character from end is: "+nthFromEnd);
        // // sll.removeDuplicate();
        // sll.traverseList();
        // // sll.removeGivenKey('a');
        // // ⚠️ Do not run traverse SSL or any other method of SLL, without removing the cycle in the SLL.
        // sll.createCycleInSSL();
        // boolean hasCycle=sll.detectCycleInSLL();
        // if(hasCycle){
        //     System.out.println("⚠️ SLL has cycles, remove it ASAP.");
        // }else{
        //     System.out.println("SLL do not have any cycle.");
        // }
        // SinglyLinkedList.ListNode<Character> startNodeOfCycle=sll.getTheStartOfTheCycle();
        // if(startNodeOfCycle!=null){
        //     System.out.println(startNodeOfCycle.data);
        // }

        // sll.removeTheCycleFromSLL();
        // sll.traverseList();

        IntegerSinglyLinkedList issl=new IntegerSinglyLinkedList(null);
        IntegerSinglyLinkedList issl2=new IntegerSinglyLinkedList(null);

        Random random = new Random();

        for(int x=0;x<20;x++){
            int randomInt=random.nextInt(10)+1;
            IntegerSinglyLinkedList.ListNode newNode=new IntegerSinglyLinkedList.ListNode(randomInt);
            issl.addNodeInSorted(newNode);
        }

        issl.traverseList();
        System.out.println();

        for(int x=0;x<20;x++){
            int randomInt=random.nextInt(10)+1;
            IntegerSinglyLinkedList.ListNode newNode=new IntegerSinglyLinkedList.ListNode(randomInt);
            issl2.addNodeInSorted(newNode);
        }

        issl2.traverseList();
        System.out.println();

        issl.mergeWithAnotherLinkedList(issl2);

        issl.traverseList();

    }

}
