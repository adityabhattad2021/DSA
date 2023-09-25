import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

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

        int totalProfit=0;
        System.out.println("Selected values: ");
        for(int b=0;b<selectedArray.size();b++){
            System.out.println(data[selectedArray.get(b)][2]);
            totalProfit += Integer.parseInt(data[selectedArray.get(b)][3]);
        }

        System.out.println("Total profit is: "+totalProfit);

        br.close();
    }
}
