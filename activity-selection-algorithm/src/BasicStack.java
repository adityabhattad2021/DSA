import java.util.ArrayList;

public class BasicStack {
    
    private ArrayList<Integer> stack = new ArrayList<>();

    public void push(Integer data){
        stack.add(data);
    }

    public int pop(){
        int data = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return data;
    }

    public int sumOfStack(){
        int sum=0;
        for(int data:stack){
            sum+=data;
        }
        return sum;
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void printStack(){
        for(int data:stack){
            System.out.println(data);
        }
    }

}
