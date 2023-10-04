import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        // System.out.println(System.getProperty("user.dir"));

        // // read the CSV
        // BufferedReader br = new BufferedReader(new FileReader("/workspaces/DSA/activity-selection-algorithm/data/input.csv"));
        // String line;
        // String[][] data = new String[11][4];
        // int i=0;
        // while((line=br.readLine())!=null){
        //     if(i==0){
        //         i++;
        //         continue;
        //     }
        //     String[] buffer=line.split(",");
        //     data[i-1][0]=buffer[0];
        //     data[i-1][1]=buffer[1];
        //     data[i-1][2]=buffer[2];
        //     data[i-1][3]=buffer[3];
        //     i++;
        // }
        

        // printArray(data);

        // // Sorting the data based on finish time
        // for(int l=0;l<data.length;l++){
        //     for(int m=0;m<data.length-1;m++){
        //         if(Integer.parseInt(data[m][1])>Integer.parseInt(data[m+1][1])){
        //             String[] temp = data[m];
        //             data[m]=data[m+1];
        //             data[m+1]=temp;
        //         }
        //     }
        // }

        // System.out.println("After sorting: ");
        // printArray(data);

        // // Index 0 for start time
        // // Index 1 for finish time
        // // Index 2 for profit.
        // int selectedIndex=0;
        // ArrayList<Integer> selectedArray = new ArrayList<>();
        // selectedArray.add(0);
        // for(int a = 1;a<data.length;a++){
        //     // Checking if the finish time of the selected activity is greater than start time of the next
        //     if(Integer.parseInt(data[selectedIndex][1])<Integer.parseInt(data[a][0])){
        //         selectedArray.add(a);
        //         selectedIndex=a;
        //     }
        // }

        // int totalProfit=0;
        // System.out.println("Selected values: ");
        // for(int b=0;b<selectedArray.size();b++){
        //     System.out.println(data[selectedArray.get(b)][2]);
        //     totalProfit += Integer.parseInt(data[selectedArray.get(b)][3]);
        // }

        // System.out.println("Total profit is: "+totalProfit);


        // // Write the selected values to a file
        // BufferedWriter bw = new BufferedWriter(new FileWriter("/workspaces/DSA/activity-selection-algorithm/data/output.csv"));

        // bw.write("Start Time, Finish Time,Activity Name, Profit");
        // bw.newLine();
        // for(int c=0;c<selectedArray.size();c++){
        //     bw.write(data[selectedArray.get(c)][0]+","+data[selectedArray.get(c)][1]+","+data[selectedArray.get(c)][2]+","+data[selectedArray.get(c)][3]);
        //     bw.newLine();
        // }

        // bw.write("Total Profit: "+totalProfit);

        // bw.close();
        // br.close();



        // Part 2, Given three stacks of the positive numbers, the task is to find the possible equal maximum sum
        // of the stacks with the removal of top elements allowed.
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

    public static void printArray(String[][] data){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }
}
