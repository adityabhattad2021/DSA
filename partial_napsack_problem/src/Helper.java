import java.util.*;

public class Helper {

    public static void sortArray(double[][] arrayToSort, boolean ascending) {
        for (int x = 0; x < arrayToSort.length; x++) {
            for (int y = 0; y < arrayToSort.length - x - 1; y++) {
                if (ascending) {
                    if (arrayToSort[y][1] > arrayToSort[y + 1][1]) {
                        double[] temp = new double[arrayToSort[y].length];
                        System.arraycopy(arrayToSort[y], 0, temp, 0, arrayToSort[y].length);
                        System.arraycopy(arrayToSort[y + 1], 0, arrayToSort[y], 0, arrayToSort[y].length);
                        System.arraycopy(temp, 0, arrayToSort[y + 1], 0, temp.length);
                    }
                } else {
                    if (arrayToSort[y][1] < arrayToSort[y + 1][1]) {
                        double[] temp = new double[arrayToSort[y].length];
                        System.arraycopy(arrayToSort[y], 0, temp, 0, arrayToSort[y].length);
                        System.arraycopy(arrayToSort[y + 1], 0, arrayToSort[y], 0, arrayToSort[y].length);
                        System.arraycopy(temp, 0, arrayToSort[y + 1], 0, temp.length);
                    }
                }
            }
        }
    }

    public static void printArray(double[][] arrayToPrint) {
        for (int x = 0; x < arrayToPrint.length; x++) {
            System.out.printf("%.2f ", arrayToPrint[x][1]);
        }
    }

    public static Result returnSelectedBag(double[][] sortedArry, double[][] weightArray, double capacity) {
        ArrayList<double[]> selectedArray = new ArrayList<>();
        int x, y = 0;
        double fraction;
        outerloop: for (x = 0; x < sortedArry.length && capacity > 0; x++) {
            // System.out.printf("|%.2f %.2f|",sortedArry[x][1],capacity);
            for (y = 0; y < weightArray.length; y++) {
                if (weightArray[y][0] == sortedArry[x][0]) {
                    if (weightArray[y][1] < capacity) {
                        selectedArray.add(sortedArry[x]);
                        capacity -= weightArray[y][1];
                    } else {
                        break outerloop;
                    }
                }
            }
        }
        // Adding one extra element, always even if there is no capacity.
        selectedArray.add(sortedArry[x]);
        if (capacity > 0) {
            fraction = capacity / weightArray[y][1];
        } else {
            fraction = 0;
        }

        Result result = new Result(selectedArray, fraction);
        return result;
    }

    public static double calculateProfit(Result result, double[][] profitArray) {
        ArrayList<double[]> selectedArray = result.getSelectedArray();
        double profit = 0;
        for (int y = 0; y < selectedArray.size(); y++) {
            if (y != selectedArray.size() - 1) {
                for (int x = 0; x < profitArray.length; x++) {
                    if (profitArray[x][0] == selectedArray.get(y)[0]) {
                        profit += profitArray[x][1];
                    }
                }
                // for last element.
            } else {
                for (int x = 0; x < profitArray.length; x++) {
                    if (profitArray[x][0] == selectedArray.get(y)[0]) {
                        profit += profitArray[x][1] * result.getFraction();
                    }
                }
            }
        }
        return profit;
    }

}
