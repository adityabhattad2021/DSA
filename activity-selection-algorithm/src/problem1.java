import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class problem1 {


    public static void main(String[] args) throws Exception{
         System.out.println(System.getProperty("user.dir"));

        // read the CSV
        BufferedReader br = new BufferedReader(new FileReader("/workspaces/DSA/activity-selection-algorithm/data/input.csv"));
        String line;
        String[][] data = new String[11][4];
        int i=0;
        while((line=br.readLine())!=null){
            if(i==0){
                i++;
                continue;
            }
            String[] buffer=line.split(",");
            data[i-1][0]=buffer[0];
            data[i-1][1]=buffer[1];
            data[i-1][2]=buffer[2];
            data[i-1][3]=buffer[3];
            i++;
        }
        
        sortArry(data);
        ArrayList<Integer> selectedArray=activitySelectionPorblem(data);
        Integer totalProfit = getTotalProfit(data, selectedArray);
        
        // Write the selected values to a file
        BufferedWriter bw = new BufferedWriter(new FileWriter("/workspaces/DSA/activity-selection-algorithm/data/output.csv"));

        bw.write("Start Time, Finish Time,Activity Name, Profit");
        bw.newLine();
        for(int c=0;c<selectedArray.size();c++){
            bw.write(data[selectedArray.get(c)][0]+","+data[selectedArray.get(c)][1]+","+data[selectedArray.get(c)][2]+","+data[selectedArray.get(c)][3]);
            bw.newLine();
        }

        bw.write("Total Profit: "+totalProfit);
        bw.close();
        br.close();
    }

    public static void printArray(String[][] data){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    } 
    
    public static void sortArry(String[][] data) {
        for(int l=0;l<data.length;l++){
            for(int m=0;m<data.length-1;m++){
                if(Integer.parseInt(data[m][1])>Integer.parseInt(data[m+1][1])){
                    String[] temp = data[m];
                    data[m]=data[m+1];
                    data[m+1]=temp;
                }
            }
        }
    }

    public static ArrayList<Integer> activitySelectionPorblem(String[][] data){
        // Index 0 for start time
        // Index 1 for finish time
        // Index 2 for profit.
        int selectedIndex=0;
        ArrayList<Integer> selectedArray = new ArrayList<>();
        selectedArray.add(0);
        for(int a = 1;a<data.length;a++){
            // Checking if the finish time of the selected activity is greater than start time of the next
            if(Integer.parseInt(data[selectedIndex][1])<Integer.parseInt(data[a][0])){
                selectedArray.add(a);
                selectedIndex=a;
            }
        }
        return selectedArray;
    }

    public static Integer getTotalProfit(String[][] data,ArrayList<Integer> selectedArray){
        int totalProfit=0;
        System.out.println("Selected values: ");
        for(int b=0;b<selectedArray.size();b++){
            System.out.println(data[selectedArray.get(b)][2]);
            totalProfit += Integer.parseInt(data[selectedArray.get(b)][3]);
        }

        System.out.println("Total profit is: "+totalProfit);
        return totalProfit;
    }
}
