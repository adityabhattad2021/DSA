// Linked List Implementation of the stack.

import java.util.NoSuchElementException;

public class Stack<T> {

    private class StackNode{
        T value;
        StackNode next;

        public StackNode(T value, StackNode next) {
            this.value = value;
            this.next = next;
        }
    }

    private StackNode top;

    public void push(T ele){
        StackNode s = new StackNode(ele,null);
        if(top==null){
            top = s;
        }else{
            s.next=top;
            top = s;
        }
    }

    public T pop(){
        if (top == null) {
            throw new NoSuchElementException("Stack is empty.");
        }
        T ele = top.value;

        // I guess java garbage collector will take care of it...
        top = top.next;
        return ele;
    }

    public T peek(){
        if(top==null){
            throw new NoSuchElementException("Stack is empty.");
        }
        return top.value;
    }

    public boolean isEmpty(){
        return top==null;
    }

}