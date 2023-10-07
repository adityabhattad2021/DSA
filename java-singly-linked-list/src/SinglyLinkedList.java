import java.util.Random;

public class SinglyLinkedList<T> {

    public ListNode<T> head;
    public ListNode<T> end;

    public static class ListNode<T> {

        public T data;
        public ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
            this.next = null;
        }

    }

    public SinglyLinkedList(T data) {
        SinglyLinkedList.ListNode<T> node = new SinglyLinkedList.ListNode<T>(data);
        this.head = node;
        this.end = node;
    }

    public void addNode(T data) {
        SinglyLinkedList.ListNode<T> node = new SinglyLinkedList.ListNode<T>(data);
        if (head == null) {
            head = node;
            end = node;
        } else {
            end.next = node;
            end = node;
        }
    }

    public void traverseList() {
        ListNode<T> pointer = head;
        while (pointer != null) {
            if (pointer.next != null) {
                System.out.print(pointer.data + "-->");
            } else {
                System.out.print(pointer.data);
            }
            pointer = pointer.next;
        }
        System.out.println();
    }

    // Find length of SLL
    public int getLength() {
        int counter = 1;
        SinglyLinkedList.ListNode<T> pointer = head;
        while (pointer != null) {
            pointer = pointer.next;
            if (pointer != null) {
                counter++;
            }
        }
        return counter;
    }

    public void printLength() {
        int length = this.getLength();
        System.out.println("Length of the list is " + length + ".");
    }

    // Insert node at the start
    public void addNodeAtStart(T data) {
        SinglyLinkedList.ListNode<T> node = new SinglyLinkedList.ListNode<T>(data);
        node.next = head;
        head = node;
    }

    // Insert node at the given position
    public void addAtGivenPos(T data, int pos) {
        int length = this.getLength();
        if (pos > length + 1 || pos < 1) {
            System.out.println("Invalid position: " + pos);
            return;
        }
        if (pos == 1) {
            this.addNodeAtStart(data);
            return;
        }
        SinglyLinkedList.ListNode<T> newNode = new SinglyLinkedList.ListNode<T>(data);
        SinglyLinkedList.ListNode<T> holder1 = null;
        SinglyLinkedList.ListNode<T> holder2 = this.head;
        int count = 1;
        while (holder2 != null && count < pos) {
            holder1 = holder2;
            holder2 = holder2.next;
            count++;
        }
        holder1.next = newNode;
        newNode.next = holder2;
        if (newNode.next == null) {
            this.end = newNode;
        }
    }

    // Delete first node of SLL
    public void deleteFirstNote() {
        SinglyLinkedList.ListNode<T> holder = this.head;
        this.head = this.head.next;
        holder.next = null;
    }

    // Delete last node of SLL
    public void deleteLastNode() {
        SinglyLinkedList.ListNode<T> holder1 = null;
        SinglyLinkedList.ListNode<T> holder2 = this.head;
        while (holder2.next != null) {
            holder1 = holder2;
            holder2 = holder2.next;
        }
        holder1.next = null;
        this.end = holder1;
    }

    // Delete node at given position
    public void deleteAtPos(int pos) {
        if (pos < 1 || pos > this.getLength()) {
            return;
        }
        if (pos == 1) {
            this.deleteFirstNote();
            return;
        }
        SinglyLinkedList.ListNode<T> holder1 = null;
        SinglyLinkedList.ListNode<T> holder2 = this.head;
        int count = 1;
        while (holder2.next != null && count < pos) {
            holder1 = holder2;
            holder2 = holder2.next;
            count++;
        }
        holder1.next = holder2.next;
        holder2.next = null;
        if (holder1.next == null) {
            this.end = holder1;
        }
    }

    // Search an element in a singly linked list.
    public int searchForElement(T ele) {
        SinglyLinkedList.ListNode<T> holder = this.head;
        int counter = 0;
        while (holder != null) {
            counter++;
            if (holder.data.equals(ele)) {
                System.out.println("Element was found at position: " + counter);
                return counter;
            }
            holder = holder.next;
        }
        System.out.println("Element not found.");
        return -1;
    }

    // Reverse the singly linked list
    public void reverseTheList() {
        if (this.getLength() <= 1) {
            return;
        }
        if (this.getLength() == 2) {
            this.end.next = this.head;
            this.head = this.end;
            this.end = this.end.next;
            this.end.next = null;
            return;
        }
        SinglyLinkedList.ListNode<T> holder1 = head;
        SinglyLinkedList.ListNode<T> holder2 = holder1.next;
        SinglyLinkedList.ListNode<T> holder3 = holder2.next;
        while (holder2 != null) {
            holder2.next = holder1;
            holder1 = holder2;
            holder2 = holder3;
            if (holder3 != null) {
                holder3 = holder3.next;
            }
        }
        // We know holder 3 has to be null, therefore we can use it as temp.
        holder3 = this.head;
        this.head = this.end;
        this.end = holder3;
        this.end.next = null;
    }

    // Find middle element in a singly linked list
    public T findMiddleElement() {
        double length = this.getLength() / 2;
        double lengthD = (double) this.getLength() / 2;
        double diff = lengthD - length;
        int middle;
        if (diff == 0) {
            middle = (int) length;
        } else {
            middle = (int) length + 1;
        }
        SinglyLinkedList.ListNode<T> holder = head;
        int counter = 1;
        while (counter < middle) {
            counter++;
            holder = holder.next;
        }
        return holder.data;
    }

    // Find the nth node from the end of singly linked list. (Without reversing the
    // list😉)
    public T findNthNodeFromEnd(int n) {
        int length = this.getLength();
        int pos = (length + 1) - n;
        SinglyLinkedList.ListNode<T> holder = this.head;
        int counter = 1;
        while (counter < pos) {
            counter++;
            holder = holder.next;
        }
        return holder.data;
    }

    // Remove duplicate from singly linked list (is it possible to do it in time
    // complexity better than O(n^2)).
    public void removeDuplicate() {
        SinglyLinkedList.ListNode<T> holder = this.head;
        int counter = 1;
        while (holder != null && holder.next != null) {
            int counter1 = counter + 1;
            SinglyLinkedList.ListNode<T> holder1 = holder.next;
            while (counter1 <= this.getLength()) {
                boolean flag = false;
                while (holder1 != null) {
                    if (holder1.data.equals(holder.data)) {
                        flag = true;
                        break;
                    }
                    counter1++;
                    holder1 = holder1.next;
                }
                if (flag) {
                    this.deleteAtPos(counter1);
                    counter1 = counter + 1;
                    holder1 = holder.next;
                }
            }
            counter++;
            holder = holder.next;
        }
    }

    // Remove a key from singly linked list.
    public void removeGivenKey(T key){
        SinglyLinkedList.ListNode<T> temp = head;
        int counter=1;
        while(temp.next!=null){
            if(temp.data.equals(key)){
                this.deleteAtPos(counter);
                return;
            }
            temp=temp.next;
            counter++;
        }
    }

    // To detect a loop in singly linked list, first i will have to create a loop in SLL.
    public void createCycleInSSL(){
        Random random = new Random();
        int randomLength=random.nextInt(this.getLength())+1;
        SinglyLinkedList.ListNode<T> temp = head;
        for(int x=0;x<randomLength;x++){
            temp=temp.next;
        }
        this.end.next=temp;
    }

    // Detect a loop in a singly linked list. (Using Flyod Cycle detection algorithm)
    public boolean detectCycleInSLL(){
        SinglyLinkedList.ListNode<T> fastPointer=head;
        SinglyLinkedList.ListNode<T> slowPointer=head;
        while(fastPointer!=null && fastPointer.next!=null){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
            if(slowPointer==fastPointer){
                return true;
            }
        }
        return false;
    }

    // Find the start of the loop.
    public SinglyLinkedList.ListNode<T> getTheStartOfTheCycle(){
        SinglyLinkedList.ListNode<T> fastPointer=head;
        SinglyLinkedList.ListNode<T> slowPointer=head;
        while(fastPointer!=null && fastPointer.next!=null){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
            if(slowPointer==fastPointer){
                // Get the startNode.
                SinglyLinkedList.ListNode<T> temp=head;
                while(temp!=slowPointer){
                    temp=temp.next;
                    slowPointer=slowPointer.next;
                }
                return temp;
            }
        }

        // If there is a cycle this line should never be reached.
        return null;
    }

    // Remove a loop from SLL
    public void removeTheCycleFromSLL(){
        SinglyLinkedList.ListNode<T> fastPointer=head;
        SinglyLinkedList.ListNode<T> slowPointer=head;
        while(fastPointer!=null && fastPointer.next!=null){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
            // Detecting the cycle
            if(fastPointer==slowPointer){
                SinglyLinkedList.ListNode<T> temp = head;
                while(temp.next!=slowPointer.next){
                    temp=temp.next;
                    slowPointer=slowPointer.next;
                }
                slowPointer.next=null;
                System.out.println("Successfully removed cycle from😀");
                return;
            }
        }
    }

    

}
