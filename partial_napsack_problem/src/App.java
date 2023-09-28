import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

        public static void main(String[] args) throws Exception {
                double capacityOfTheTruck = 850;
                double[][] weightOfEachBox = { { 0, 7 }, { 1, 0 }, { 2, 30 }, { 3, 22 }, { 4, 80 }, { 5, 94 },
                                { 6, 11 },
                                { 7, 81 }, { 8, 70 }, { 9, 64 }, { 10, 59 }, { 11, 18 }, { 12, 0 }, { 13, 36 },
                                { 14, 3 }, { 15, 8 },
                                { 16, 15 }, { 17, 42 }, { 18, 9 }, { 19, 0 }, { 20, 42 }, { 21, 47 }, { 22, 52 },
                                { 23, 32 },
                                { 24, 26 }, { 25, 48 }, { 26, 55 }, { 27, 6 }, { 28, 29 }, { 29, 84 }, { 30, 2 },
                                { 31, 4 }, { 32, 18 },
                                { 33, 56 }, { 34, 7 }, { 35, 29 }, { 36, 93 }, { 37, 44 }, { 38, 71 }, { 39, 3 },
                                { 40, 86 },
                                { 41, 66 }, { 42, 31 }, { 43, 65 }, { 44, 0 }, { 45, 79 }, { 46, 20 }, { 47, 65 },
                                { 48, 52 },
                                { 49, 13 } };

                double[][] profitOfEachBox = { { 0, 360 }, { 1, 83 }, { 2, 59 }, { 3, 130 }, { 4, 431 }, { 5, 67 },
                                { 6, 230 },
                                { 7, 52 }, { 8, 93 }, { 9, 125 }, { 10, 670 }, { 11, 892 }, { 12, 600 }, { 13, 38 },
                                { 14, 48 },
                                { 15, 147 }, { 16, 78 }, { 17, 256 }, { 18, 63 }, { 19, 17 }, { 20, 120 }, { 21, 164 },
                                { 22, 432 },
                                { 23, 35 }, { 24, 92 }, { 25, 110 }, { 26, 22 }, { 27, 42 }, { 28, 50 }, { 29, 323 },
                                { 30, 514 },
                                { 31, 28 }, { 32, 87 }, { 33, 73 }, { 34, 78 }, { 35, 15 }, { 36, 26 }, { 37, 78 },
                                { 38, 210 },
                                { 39, 36 }, { 40, 85 }, { 41, 189 }, { 42, 274 }, { 43, 43 }, { 44, 33 }, { 45, 10 },
                                { 46, 19 },
                                { 47, 389 }, { 48, 276 }, { 49, 312 } };

                // Perform fractional knapsack
                double result1 = performFractionalKnapsack(weightOfEachBox, profitOfEachBox, capacityOfTheTruck,
                                "Fractional knapsack for profit by weight ratio");
                double result2 = performFractionalKnapsack(weightOfEachBox, profitOfEachBox, capacityOfTheTruck,
                                "Fractional Knapsack for minimum weight");
                double result3 = performFractionalKnapsack(weightOfEachBox, profitOfEachBox, capacityOfTheTruck,
                                "Fractional Knapsack for maximum profit");

                // Write the data to csv
                List<String[]> data = new ArrayList<>();
                data.add(new String[] { "Approach", "Result" });
                data.add(new String[] { "Fractional knapsack for profit by weight ratio", String.valueOf(result1) });
                data.add(new String[] { "Fractional Knapsack for minimum weight", String.valueOf(result2) });
                data.add(new String[] { "Fractional Knapsack for maximum profit", String.valueOf(result3) });

                BufferedWriter writer = new BufferedWriter(
                                new FileWriter("/workspaces/DSA/partial_napsack_problem/data/result.csv"));
                for (String[] rowData : data) {
                        writer.write(String.join(",", rowData));
                        writer.newLine();
                }
                writer.close();
        }

        public static double performFractionalKnapsack(double[][] weightArray, double[][] profitArray,
                        double capacityOfTheTruck, String approachName) {
                // Create copy of weightArray and profitArray
                double[][] weightArrayCopy = new double[weightArray.length][weightArray[0].length];
                double[][] profitArrayCopy = new double[profitArray.length][profitArray[0].length];
                System.arraycopy(weightArray, 0, weightArrayCopy, 0, weightArray.length);
                System.arraycopy(profitArray, 0, profitArrayCopy, 0, profitArray.length);
                double fResult = 0;

                if (approachName.equals("Fractional Knapsack for maximum profit")) {
                        // Sort weightArrayCopy in ascending order
                        Helper.sortArray(profitArrayCopy, false);

                        // Get selected bag
                        Result result = Helper.returnSelectedBag(profitArrayCopy, weightArray, capacityOfTheTruck);

                        // Calculate profit
                        fResult = Helper.calculateProfit(result, profitArray);

                        System.out.printf("\n\n======Fractional Knapsack for maximum profit======\n");
                        // Print result
                        System.out.printf("======%.2f======\n\n", fResult);
                } else if (approachName.equals("Fractional Knapsack for minimum weight")) {
                        // Sort weightArrayCopy in ascending order
                        Helper.sortArray(weightArrayCopy, true);

                        // Get selected bag
                        Result result = Helper.returnSelectedBag(weightArrayCopy, weightArray, capacityOfTheTruck);

                        // Calculate profit
                        fResult = Helper.calculateProfit(result, profitArray);

                        System.out.printf("\n\n======Fractional Knapsack for minimum weight======\n");
                        // Print result
                        System.out.printf("======%.2f======\n\n", fResult);

                } else if (approachName.equals("Fractional knapsack for profit by weight ratio")) {

                        // Calculate profit/weight ratio array
                        double[][] profitWeightRatioArray = new double[profitArray.length][2];
                        for (int x = 0; x < profitArray.length; x++) {
                                profitWeightRatioArray[x][0] = profitArray[x][0];
                                profitWeightRatioArray[x][1] = profitArray[x][1] / weightArray[x][1];
                        }

                        Helper.sortArray(profitWeightRatioArray, false);

                        // Get selected bag
                        Result result = Helper.returnSelectedBag(profitWeightRatioArray, weightArray,
                                        capacityOfTheTruck);

                        // Calculate profit
                        fResult = Helper.calculateProfit(result, profitArray);

                        System.out.printf("\n\n======Fractional knapsack for Profit/Weight ratio======\n");
                        // Print result
                        System.out.printf("======%.2f======\n\n", fResult);

                } else {
                        System.out.println("Invalid approach name.");
                }
                return fResult;

        }

}
