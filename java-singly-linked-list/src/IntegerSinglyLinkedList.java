public class IntegerSinglyLinkedList {

    public ListNode head;
    public ListNode tail;

    public static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public IntegerSinglyLinkedList(ListNode firstNode){
        this.head=firstNode;
        this.tail=firstNode;
    }

    public void addNodeInSorted(ListNode ln){
        if(this.head==null){
            this.head=ln;
            this.tail=ln;
            return;
        }
        // if the data => head, add a node to the end, if data < head add node to start.
        if(ln.data<=this.head.data){
            ln.next=this.head;
            this.head=ln;
        }else{
            IntegerSinglyLinkedList.ListNode temp=head;
            while(temp!=null && temp.next!=null && ln.data>temp.next.data){
                temp=temp.next;
            }
            if(temp.next==null){
                temp.next=ln;
                this.tail=ln;
            }else{
                ln.next=temp.next;
                temp.next=ln;
            }
        }
    }

    public void traverseList(){
        IntegerSinglyLinkedList.ListNode temp=head;
        while(temp!=null){
            if(temp.next!=null){
                System.out.printf("%d--->",temp.data);
            }else{
                System.out.printf("%d--->null",temp.data);
            }
            temp=temp.next;
        }
    }
}
