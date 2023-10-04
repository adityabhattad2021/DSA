public class problem2 {
    public static void main(String[] args) {

        // Initialize stacks and push the elements into the stack
        BasicStack stack1 = new BasicStack();
        BasicStack stack2 = new BasicStack();
        BasicStack stack3 = new BasicStack();

        int[] data1 = {5,8,5,3};
        int[] data2 = {5,6,9,4,2,2};
        int[] data3 = {5,3,2,1,2};

        for(int x=0;x<data1.length;x++){
            stack1.push(data1[data1.length-x-1]);
        }
        for(int x=0;x<data2.length;x++){
            stack2.push(data2[data2.length-x-1]);
        }
        for(int x=0;x<data3.length;x++){
            stack3.push(data3[data3.length-x-1]);
        }


        // Algorithm to find possible equal maximum sum of the stacks.
        boolean flag=false;
        while(!stack1.isEmpty() && !stack2.isEmpty() && !stack3.isEmpty()){
            
            if(stack1.sumOfStack()==stack2.sumOfStack() && stack2.sumOfStack()==stack3.sumOfStack()){
                System.out.println("Possible equal maximum sum of the stacks is: "+stack1.sumOfStack());
                System.out.println("Stack 1 data");
                stack1.printStack();
                System.out.println("Stack 2 data");
                stack2.printStack();
                System.out.println("Stack 3 data");
                stack3.printStack();
                flag=true;
                break;
            }
            if(stack1.sumOfStack() > stack2.sumOfStack()){
                if(stack1.sumOfStack()>stack3.sumOfStack()){
                    stack1.pop();
                }else{
                    stack3.pop();
                }
            }else{
                if(stack2.sumOfStack()>stack3.sumOfStack()){
                    stack2.pop();
                }else{
                    stack3.pop();

                }
            }
        }
        if(!flag){
            System.out.println("No possible equal maximum sum of the stacks found!");
        }

    }
}
