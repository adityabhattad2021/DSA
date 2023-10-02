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
        int counter = 0;
        SinglyLinkedList.ListNode<T> node = new SinglyLinkedList.ListNode<T>(data);
        SinglyLinkedList.ListNode<T> pointer = head;
        while (pointer != null) {
            counter++;
            if (counter == pos - 1) {
                if (counter == 0) {
                    node.next = pointer;
                    this.head = node;
                } else if (counter == length) {
                    pointer.next = node;
                    this.end = node;
                } else {
                    node.next = pointer.next;
                    pointer.next = node;
                }
            }
            pointer = pointer.next;
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
    }

}
