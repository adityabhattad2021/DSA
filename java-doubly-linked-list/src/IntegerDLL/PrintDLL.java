package IntegerDLL;

public class PrintDLL extends IntegerDLL {
    
    public void printForward(){
        if(this.isEmpty()){
            System.out.println("List is empty.");
            return;
        }
        PrintDLL.ListNode temp=this.head;
        while(temp!=null){
            System.out.println(temp.data+" --> ");
            temp=temp.next;
        }
        System.out.println("null");
    }   

    public void printBackward(){
        if(this.isEmpty()){
            System.out.println("List is empty.");
            return; 
        }
        PrintDLL.ListNode temp = this.tail;
        while(temp!=null){
            System.out.println(temp.data+" --> ");
            temp=temp.previous;
        }
        System.out.println("null");
    }

}
