import java.util.NoSuchElementException;

public class Queue<T>{
    private class QueueNode{

        T value;
        QueueNode next;

        QueueNode(T value,QueueNode next){
            this.value = value;
            this.next = next;
        }
    }

    private QueueNode head;

    public void enQueue(T ele){
        QueueNode node = new QueueNode(ele,null);
        if(head==null){
            head = node;
        }else{
            QueueNode temp=head;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next=node;
        }
    }

    public T deQueue(){
        if(head==null){
            throw new NoSuchElementException("Stack is empty.");
        }
        T ele = head.value;
        head = head.next;
        return ele;
    }

    public boolean isEmpty(){
        return head == null;
    }
}