public class App {
    public static void main(String[] args) throws Exception {
        /*Find the similarity between the given X and Y sequence. */
        String y = "AGCCCTAAGGGCTACCTAGCTT";
        String x = "GACAGCCTACAAGCGTTAGCTTG";
        int m = x.length();
        int n = y.length();
        // 1 for diagonal,2 for side,3 for up,0 for halt
        int[][][] cost = new int[m + 1][n + 1][2];

        // Initialize first column
        for (int i = 0; i <= m; i++) {
            cost[i][0][0] = 0;
            cost[i][0][1] = 0;
        }

        // Initialize first row
        for (int j = 0; j <= n; j++) {
            cost[0][j][0] = 0;
            cost[0][j][1] = 0;
        }

        // Populate matrix
        for (int i = 1; i <= m; i++) {
            char c1 = x.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = y.charAt(j - 1);
                if (c1 == c2) {
                    if(i==1 && j==4){
                        System.out.println("++++++++");
                        System.out.println(x.charAt(i - 1));
                        System.out.println(y.charAt(j - 1));
                    }
                    cost[i][j][0] = cost[i - 1][j - 1][0]+1;
                    cost[i][j][1] = 1;
                } else {
                    int up = cost[i - 1][j][0];
                    int side = cost[i][j - 1][0];
                    cost[i][j][0] = Math.max(up, side);
                    cost[i][j][1] = Math.max(up, side)==up ? 3 : 2;
                }
            }
        }

        // Print matrix
        for (int[][] row : cost) {
            for (int col[] : row) {
                System.out.printf("%3d/%d", col[0],col[1]);
            }
            System.out.println();
        }

        // Backtrack
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            char c2 = y.charAt(j - 1);
            if (cost[i][j][1]==1) {
                sb.append(c2);
                i--;
                j--;
            } else {
                if(cost[i][j][1]==2){
                    j--;    
                }else if(cost[i][j][1]==3){
                    i--;
                }
            }
        }

        System.out.println(sb.reverse().toString());


        /*A subsequence of a given sequence is palindrome if it reads the same when read
        from left to right or right to left. Design an algorithm that take a sequence X[1...n].
        Find all the possible palindrome sub-sequences for the given DNA sequence */
        String sequence = "ACGTGTCAAAATCG";
        String reverseSequence = new StringBuilder(sequence).reverse().toString();

        System.out.println("The sequence is: " + sequence);
        System.out.println("The reverse sequence is: " + reverseSequence);

        int[][] palindrome = new int[sequence.length() + 1][reverseSequence.length() + 1];

        for (int k = 0; k <= sequence.length(); k++) {
            palindrome[k][0] = 0;
        }

        for (int l = 0; l <= reverseSequence.length(); l++) {
            palindrome[0][l] = 0;
        }

        for (int k = 1; k <= sequence.length(); k++) {
            char c1 = sequence.charAt(k - 1);
            for (int l = 1; l <= reverseSequence.length(); l++) {
                char c2 = reverseSequence.charAt(l - 1);
                if (c1 == c2) {
                    palindrome[k][l] = palindrome[k - 1][l - 1] + 1;
                } else {
                    int up = palindrome[k - 1][l];
                    int side = palindrome[k][l - 1];
                    palindrome[k][l] = Math.max(up, side);
                }
            }
        }

        for (int[] row : palindrome) {
            for (int col : row) {
                System.out.printf("%3d", col);
            }
            System.out.println();
        }

        i = sequence.length();
        j = reverseSequence.length();
        StringBuilder sb1 = new StringBuilder();

        // Finding all the possible palindrome sub-sequences
        while (i > 0 && j > 0) {
            char c1 = x.charAt(i - 1);
            char c2 = y.charAt(j - 1);
            if (c1 == c2) {
                sb1.append(c1);
                i--;
                j--;
            } else {
                int up = palindrome[i - 1][j];
                int side = palindrome[i][j - 1];
                if (up > side) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        System.out.println(sb1.reverse().toString());        
    }


}
