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

}
