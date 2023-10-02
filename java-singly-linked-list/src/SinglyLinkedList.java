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
        System.out.println("Length of the list is " + counter + ".");
        return counter;
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
        if(pos==1){
            this.addNodeAtStart(data);
            return;
        }
        SinglyLinkedList.ListNode<T> newNode=new SinglyLinkedList.ListNode<T>(data);
        SinglyLinkedList.ListNode<T> holder1=null;
        SinglyLinkedList.ListNode<T> holder2=this.head;
        int count=1;
        while(holder2!=null && count<pos){
            holder1=holder2;
            holder2=holder2.next;
            count++;
        }
        holder1.next=newNode;
        newNode.next=holder2;
        if(newNode.next==null){
            this.end=newNode;
        }
    }

    // Delete first node of SLL
    public void deleteFirstNote(){
        SinglyLinkedList.ListNode<T> holder = this.head;
        this.head=this.head.next;
        holder.next=null;
    }


    // Delete last node of SLL
    public void deleteLastNode(){
        SinglyLinkedList.ListNode<T> holder1=null;
        SinglyLinkedList.ListNode<T> holder2=this.head;
        while(holder2.next!=null){
            holder1=holder2;
            holder2=holder2.next;
        }
        holder1.next=null;
        this.end=holder1;
    }

    // Delete node at given position
    public void deleteAtPos(int pos){
        if(pos<1 || pos>this.getLength()){
            return;
        }
        if(pos==1){
            this.deleteFirstNote();
            return;
        }
        SinglyLinkedList.ListNode<T> holder1=null;
        SinglyLinkedList.ListNode<T> holder2=this.head;
        int count=1;
        while(holder2.next!=null && count < pos){
            holder1=holder2;
            holder2=holder2.next;
            count++;
        }
        holder1.next=holder2.next;
        holder2.next=null;
        if(holder1.next==null){
            this.end=holder1;
        }
    }

    // Search an element in a singly linked list.
    public int searchForElement(T ele){
        SinglyLinkedList.ListNode<T> holder = this.head;
        int counter=0;
        while(holder!=null){
            counter++;
            if(holder.data.equals(ele)){
                System.out.println("Element was found at position: "+counter);
                return counter;
            }
            holder=holder.next;
        }
        System.out.println("Element not found.");
        return -1;
    }




    // Reverse the singly linked list
    
    // Find middle element in a singly linked list
    // Find the nth node from the end of singly linked list.
    // Remove duplicate from singly linked list
    // Insert a node in a sorted singly linked list


}
