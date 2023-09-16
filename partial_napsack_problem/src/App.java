public class App {

    /*
     * Problem Definition: Suppose you are a transport dealer and want to load a
     * truck
     * with different types of boxes. Assume there are 50 types of boxes (Box-1 to
     * Box-50),
     * which weigh different and that the truck has a maximum capacity (truckSize).
     * Each
     * box has a profit value associated with it. It is the commission that the
     * transporter will
     * receive after transporting the box. You can choose any box to put on the
     * truck as
     * long as the number of boxes does not exceed truckSize.
     * 
     *  Capacity of truck 850 Kgs
     *  Weight in kg for each box:
     * [7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0, 42,
     * 47, 52, 32,
     * 26, 48, 55, 6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0,
     * 79, 20, 65,
     * 52, 13]
     *  Profit in Rs for each box:
     * [ 360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147,
     * 78, 256,
     * 63, 17, 120, 164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15,
     * 26, 78,
     * 210, 36, 85, 189, 274, 43, 33, 10, 19, 389, 276,  312 ]
     */

    public static void main(String[] args) throws Exception {
        Integer capacityOfTheTruck = 850;

        Integer[][] weightOfEachBox = { { 0, 7 }, { 1, 0 }, { 2, 30 }, { 3, 22 }, { 4, 80 }, { 5, 94 }, { 6, 11 },
                { 7, 81 }, { 8, 70 }, { 9, 64 }, { 10, 59 }, { 11, 18 }, { 12, 0 }, { 13, 36 }, { 14, 3 }, { 15, 8 },
                { 16, 15 }, { 17, 42 }, { 18, 9 }, { 19, 0 }, { 20, 42 }, { 21, 47 }, { 22, 52 }, { 23, 32 },
                { 24, 26 }, { 25, 48 }, { 26, 55 }, { 27, 6 }, { 28, 29 }, { 29, 84 }, { 30, 2 }, { 31, 4 }, { 32, 18 },
                { 33, 56 }, { 34, 7 }, { 35, 29 }, { 36, 93 }, { 37, 44 }, { 38, 71 }, { 39, 3 }, { 40, 86 },
                { 41, 66 }, { 42, 31 }, { 43, 65 }, { 44, 0 }, { 45, 79 }, { 46, 20 }, { 47, 65 }, { 48, 52 },
                { 49, 13 } };

        Integer[][] profitOfEachBox = { { 0, 360 }, { 1, 83 }, { 2, 59 }, { 3, 130 }, { 4, 431 }, { 5, 67 }, { 6, 230 },
                { 7, 52 }, { 8, 93 }, { 9, 125 }, { 10, 670 }, { 11, 892 }, { 12, 600 }, { 13, 38 }, { 14, 48 },
                { 15, 147 }, { 16, 78 }, { 17, 256 }, { 18, 63 }, { 19, 17 }, { 20, 120 }, { 21, 164 }, { 22, 432 },
                { 23, 35 }, { 24, 92 }, { 25, 110 }, { 26, 22 }, { 27, 42 }, { 28, 50 }, { 29, 323 }, { 30, 514 },
                { 31, 28 }, { 32, 87 }, { 33, 73 }, { 34, 78 }, { 35, 15 }, { 36, 26 }, { 37, 78 }, { 38, 210 },
                { 39, 36 }, { 40, 85 }, { 41, 189 }, { 42, 274 }, { 43, 43 }, { 44, 33 }, { 45, 10 }, { 46, 19 },
                { 47, 389 }, { 48, 276 }, { 49, 312 } };

        // Loading using minimum weight

        // Sorting according to min weight
        for (int x = 0; x < weightOfEachBox.length; x++) {
            for (int y = 0; y < weightOfEachBox.length - x - 1; y++) {
                if (weightOfEachBox[y][1] > weightOfEachBox[y + 1][1]) {
                    Integer temp = weightOfEachBox[y][1];
                    weightOfEachBox[y][1] = weightOfEachBox[y + 1][1];
                    weightOfEachBox[y + 1][1] = temp;
                }
            }
        }

        // System.out.println("Sorted array is for min weight");
        // for (int x = 0; x < weightOfEachBox.length; x++) {
        // System.out.print(weightOfEachBox[x][1] + " ");
        // }

        Integer selectedBags[][] = new Integer[weightOfEachBox.length][2];
        Integer numOfEleInSelectedBag = 0;
        for (int x = 0; x < weightOfEachBox.length && capacityOfTheTruck > 0; x++) {
            if (capacityOfTheTruck >= weightOfEachBox[x][1]) {
                selectedBags[x][0] = weightOfEachBox[x][0];
                selectedBags[x][1] = weightOfEachBox[x][1];
                capacityOfTheTruck = capacityOfTheTruck - weightOfEachBox[x][1];
                numOfEleInSelectedBag++;
            }
        }

        boolean wasLastEle = false;
        if (capacityOfTheTruck > 0) {
            System.out.println("Truck is not full");
            // adding fraction of the last box
            selectedBags[numOfEleInSelectedBag][0] = weightOfEachBox[numOfEleInSelectedBag][0];
            numOfEleInSelectedBag++;
            wasLastEle=true;
        }

        Double totalProfit = 0.0;
        for (int x = 0; x < numOfEleInSelectedBag; x++) {
            for (int y = 0; y < profitOfEachBox.length; y++) {
                if (selectedBags[x][0] == profitOfEachBox[y][0]) { 
                    if(x<numOfEleInSelectedBag-1 && wasLastEle){
                        totalProfit = totalProfit + ((double) profitOfEachBox[y][1]* weightOfEachBox[x][1]/capacityOfTheTruck);
                    }else{
                        totalProfit = totalProfit + (double) profitOfEachBox[y][1];
                    }
                }
            }
        }
        

        System.out.println("Total Profit, using minumum weight: " + totalProfit);

        Integer capacityOfTheTruck1 = 850;

        Integer[][] weightOfEachBox1 = { { 0, 7 }, { 1, 0 }, { 2, 30 }, { 3, 22 }, { 4, 80 }, { 5, 94 }, { 6, 11 },
                { 7, 81 }, { 8, 70 }, { 9, 64 }, { 10, 59 }, { 11, 18 }, { 12, 0 }, { 13, 36 }, { 14, 3 }, { 15, 8 },
                { 16, 15 }, { 17, 42 }, { 18, 9 }, { 19, 0 }, { 20, 42 }, { 21, 47 }, { 22, 52 }, { 23, 32 },
                { 24, 26 }, { 25, 48 }, { 26, 55 }, { 27, 6 }, { 28, 29 }, { 29, 84 }, { 30, 2 }, { 31, 4 }, { 32, 18 },
                { 33, 56 }, { 34, 7 }, { 35, 29 }, { 36, 93 }, { 37, 44 }, { 38, 71 }, { 39, 3 }, { 40, 86 },
                { 41, 66 }, { 42, 31 }, { 43, 65 }, { 44, 0 }, { 45, 79 }, { 46, 20 }, { 47, 65 }, { 48, 52 },
                { 49, 13 } };

        Integer[][] profitOfEachBox1 = { { 0, 360 }, { 1, 83 }, { 2, 59 }, { 3, 130 }, { 4, 431 }, { 5, 67 },
                { 6, 230 },
                { 7, 52 }, { 8, 93 }, { 9, 125 }, { 10, 670 }, { 11, 892 }, { 12, 600 }, { 13, 38 }, { 14, 48 },
                { 15, 147 }, { 16, 78 }, { 17, 256 }, { 18, 63 }, { 19, 17 }, { 20, 120 }, { 21, 164 }, { 22, 432 },
                { 23, 35 }, { 24, 92 }, { 25, 110 }, { 26, 22 }, { 27, 42 }, { 28, 50 }, { 29, 323 }, { 30, 514 },
                { 31, 28 }, { 32, 87 }, { 33, 73 }, { 34, 78 }, { 35, 15 }, { 36, 26 }, { 37, 78 }, { 38, 210 },
                { 39, 36 }, { 40, 85 }, { 41, 189 }, { 42, 274 }, { 43, 43 }, { 44, 33 }, { 45, 10 }, { 46, 19 },
                { 47, 389 }, { 48, 276 }, { 49, 312 } };

        // Loading using maximum profit

        // Sorting according to max profit
        for (int x = 0; x < profitOfEachBox1.length; x++) {
            for (int y = 0; y < profitOfEachBox1.length - x - 1; y++) {
                if (profitOfEachBox1[y][1] < profitOfEachBox1[y + 1][1]) {
                    Integer temp = profitOfEachBox1[y][1];
                    profitOfEachBox1[y][1] = profitOfEachBox1[y + 1][1];
                    profitOfEachBox1[y + 1][1] = temp;
                }
            }
        }

        // System.out.println("Sorted array is for min weight");
        // for (int x = 0; x < profitOfEachBox1.length; x++) {
        // System.out.print(profitOfEachBox1[x][1] + " ");
        // }

        Integer selectedBags1[][] = new Integer[weightOfEachBox.length][2];
        Integer numOfEleInSelectedBag1 = 0;
        for (int x = 0; x < profitOfEachBox1.length && capacityOfTheTruck1 > 0; x++) {
            if (capacityOfTheTruck >= weightOfEachBox[x][1]) {
                selectedBags1[x][0] = profitOfEachBox1[x][0];
                selectedBags1[x][1] = weightOfEachBox1[profitOfEachBox1[x][0]][1];
                capacityOfTheTruck1 = capacityOfTheTruck1 - weightOfEachBox1[profitOfEachBox1[x][0]][1];
                numOfEleInSelectedBag1++;
            }
        }

        Integer totalProfit1 = 0;
        for (int x = 0; x < numOfEleInSelectedBag1; x++) {
            for (int y = 0; y < profitOfEachBox1.length; y++) {
                if (selectedBags1[x][0] == profitOfEachBox1[y][0]) {
                    totalProfit1 = totalProfit1 + profitOfEachBox1[y][1];
                }
            }
        }

        System.out.println("Total Profit, using maximum profit: " + totalProfit1);

        // Loading using maximum profit/weight
        Integer capacityOfTheTruck2 = 850;

        Integer[][] weightOfEachBox2 = { { 0, 7 }, { 1, 0 }, { 2, 30 }, { 3, 22 }, { 4, 80 }, { 5, 94 }, { 6, 11 },
                { 7, 81 }, { 8, 70 }, { 9, 64 }, { 10, 59 }, { 11, 18 }, { 12, 0 }, { 13, 36 }, { 14, 3 }, { 15, 8 },
                { 16, 15 }, { 17, 42 }, { 18, 9 }, { 19, 0 }, { 20, 42 }, { 21, 47 }, { 22, 52 }, { 23, 32 },
                { 24, 26 }, { 25, 48 }, { 26, 55 }, { 27, 6 }, { 28, 29 }, { 29, 84 }, { 30, 2 }, { 31, 4 }, { 32, 18 },
                { 33, 56 }, { 34, 7 }, { 35, 29 }, { 36, 93 }, { 37, 44 }, { 38, 71 }, { 39, 3 }, { 40, 86 },
                { 41, 66 }, { 42, 31 }, { 43, 65 }, { 44, 0 }, { 45, 79 }, { 46, 20 }, { 47, 65 }, { 48, 52 },
                { 49, 13 } };

        Integer[][] profitOfEachBox2 = { { 0, 360 }, { 1, 83 }, { 2, 59 }, { 3, 130 }, { 4, 431 }, { 5, 67 },
                { 6, 230 },
                { 7, 52 }, { 8, 93 }, { 9, 125 }, { 10, 670 }, { 11, 892 }, { 12, 600 }, { 13, 38 }, { 14, 48 },
                { 15, 147 }, { 16, 78 }, { 17, 256 }, { 18, 63 }, { 19, 17 }, { 20, 120 }, { 21, 164 }, { 22, 432 },
                { 23, 35 }, { 24, 92 }, { 25, 110 }, { 26, 22 }, { 27, 42 }, { 28, 50 }, { 29, 323 }, { 30, 514 },
                { 31, 28 }, { 32, 87 }, { 33, 73 }, { 34, 78 }, { 35, 15 }, { 36, 26 }, { 37, 78 }, { 38, 210 },
                { 39, 36 }, { 40, 85 }, { 41, 189 }, { 42, 274 }, { 43, 43 }, { 44, 33 }, { 45, 10 }, { 46, 19 },
                { 47, 389 }, { 48, 276 }, { 49, 312 } };

        Double profitPerWeight[][] = new Double[profitOfEachBox2.length][2];

        for (int x = 0; x < profitOfEachBox2.length; x++) {
            profitPerWeight[x][0] = (double) profitOfEachBox2[x][0];
            profitPerWeight[x][1] = (double) profitOfEachBox2[x][1]
                    / (double) weightOfEachBox2[profitOfEachBox2[x][0]][1];
        }

        // Sorting according to max profit/weight
        for (int x = 0; x < profitPerWeight.length; x++) {
            for (int y = 0; y < profitPerWeight.length - x - 1; y++) {
                if (profitPerWeight[y][1] < profitPerWeight[y + 1][1]) {
                    Double temp = profitPerWeight[y][1];
                    profitPerWeight[y][1] = profitPerWeight[y + 1][1];
                    profitPerWeight[y + 1][1] = temp;

                    temp = profitPerWeight[y][0];
                    profitPerWeight[y][0] = profitPerWeight[y + 1][0];
                    profitPerWeight[y + 1][0] = temp;
                }
            }
        }

        // System.out.println("Profit per weight");
        // for (int x = 0; x < profitPerWeight.length; x++) {
        // System.out.print(profitPerWeight[x][1] + " ");
        // }

        Integer selectedBags2[][] = new Integer[weightOfEachBox.length][2];
        Integer numOfEleInSelectedBag2 = 0;
        for (int x = 0; x < profitPerWeight.length && capacityOfTheTruck2 > 0; x++) {
            if (capacityOfTheTruck >= weightOfEachBox[x][1]) {
                selectedBags2[x][0] = profitPerWeight[x][0].intValue();
                selectedBags2[x][1] = weightOfEachBox2[profitPerWeight[x][0].intValue()][1];
                capacityOfTheTruck2 = capacityOfTheTruck2 - weightOfEachBox2[profitPerWeight[x][0].intValue()][1];
                numOfEleInSelectedBag2++;
            }
        }

        Integer totalProfit2 = 0;
        for (int x = 0; x < numOfEleInSelectedBag2; x++) {
            for (int y = 0; y < profitOfEachBox2.length; y++) {
                if (selectedBags2[x][0] == profitOfEachBox2[y][0]) {
                    totalProfit2 = totalProfit2 + profitOfEachBox2[y][1];
                }
            }
        }

        System.out.println("Total Profit, using maximum profit/weight: " + totalProfit2);
    }
}
