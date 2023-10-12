package IntegerDLL;

import main.DoublyLinkedList;

public class IntegerDLL extends DoublyLinkedList<Integer> {

    public void addAtEnd(IntegerDLL.ListNode data) {
        if (this.isEmpty()) {
            this.head = data;
            this.tail = data;
        } else {
            IntegerDLL.ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = data;
            data.previous = temp;
        }
        this.length += 1;
    }

    public void addAtStart(IntegerDLL.ListNode data) {
        if (this.isEmpty()) {
            this.head = data;
            this.tail = data;
            this.length += 1;
        }else{
            data.next=this.head;
            this.head.previous=data;
            this.head=data;
        }
        this.length+=1;
    }

}
