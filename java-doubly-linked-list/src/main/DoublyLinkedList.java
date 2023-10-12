package main;

/**
 * A doubly linked list implementation in Java.
 * @param <T> the type of elements stored in the list.
 */
public class DoublyLinkedList <T> {

    /**
     * A node in the doubly linked list.
     */
    public class ListNode {
        public T data;
        public ListNode next;
        public ListNode previous;

        /**
         * Creates a new ListNode with the given data.
         * @param data the data to store in the node.
         */
        public ListNode(T data){
            this.data = data;
            this.next=null;
            this.previous=null;
        }
    }

    protected ListNode head;
    protected ListNode tail;
    protected int length;

    /**
     * Creates an empty doubly linked list.
     */
    public DoublyLinkedList(){
        this.head=null;
        this.tail=null;
        this.length=0;
    }

    /**
     * Checks if the doubly linked list is empty.
     * @return true if the doubly linked list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return length==0;
    }

    /**
     * Returns the length of the doubly linked list.
     * @return the length of the doubly linked list.
     */
    public int getLength(){
        return length;
    }
}