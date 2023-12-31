import java.util.NoSuchElementException;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        for(int x=1;x<=10;x++){
            stack.push(x);
        }
        while(!stack.isEmpty()){
            stack1.push(stack.pop());
        }
        try{
            while(true){
                System.out.println(stack1.pop());
            }
        }catch(NoSuchElementException e){
            System.out.println("Stack is empty.");
        }
    }
}
