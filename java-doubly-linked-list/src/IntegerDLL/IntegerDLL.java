package IntegerDLL;

import main.DoublyLinkedList;

public class IntegerDLL extends DoublyLinkedList<Integer> {
    
    public void add(DoublyLinkedList<Integer>.ListNode data){
        if(this.isEmpty()){
            this.head=data;
            this.tail=data;
            this.length+=1;
        }else{
            IntegerDLL.ListNode temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=data;
            data.previous=temp;
        }
    }

}
