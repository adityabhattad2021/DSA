import java.util.ArrayList;

public class App {
    // Solving sub of subset problem using backtracking.
    public static void main(String[] args) throws Exception {
        int[] arry = {1,2,3,4,5};
        int sum = 10;
        ArrayList<Integer> subset = new ArrayList<Integer>();
        subsetSum(arry, sum, 0, subset, 0);
    }


    public static void subsetSum(int[] arry, int sum, int index, ArrayList<Integer> subset, int subIndex) {
        if(getSumOfSubset(subset) == sum) {
            for(int i = 0; i < subset.size(); i++) {
                System.out.print(subset.get(i) + " ");
            }
            System.out.println();
            return;
        }

        if(index == arry.length) {
            return;
        }

        for(int i = index; i < arry.length; i++) {
            if(getSumOfSubset(subset) + arry[i] > sum) {
                continue;
            }
            subset.add(subIndex, arry[i]);
            subsetSum(arry, sum, i + 1, subset, subIndex + 1);
            subset.remove(subIndex);
        }
    }

    public static int getSumOfSubset(ArrayList<Integer> subset) {
        int sum = 0;
        for(int i = 0; i < subset.size(); i++) {
            sum += subset.get(i);
        }
        return sum;
    }
}
