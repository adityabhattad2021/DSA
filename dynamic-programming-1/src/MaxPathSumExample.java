import java.util.HashMap;
import java.util.List;

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MaxPathSumExample {
    public static void main(String[] args) {
        int[][] grid = { { 1, 3, 12 }, { 5, 6, 2 }, { 8, 13, 4 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
                System.out.printf("%02d ", grid[i][j]);
            System.out.println();
        }
        System.out.println(maxPathSum(0, 0, grid));
    }

    public static int maxPathSum(int x, int y, int[][] grid) {
        return maxPathSum(x, y, grid, new HashMap<Pair, Integer>());
    }

    public static int maxPathSum(int x, int y, int[][] grid, HashMap<Pair, Integer> memo) {
        if (x >= grid[0].length || y >= grid.length) {
            return 0;
        }

        if (x == grid[0].length - 1 && y == grid.length - 1) {
            return grid[y][x];
        }

        Pair key = new Pair(x, y);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int down = maxPathSum(x, y + 1, grid, memo);
        int right = maxPathSum(x + 1, y, grid, memo);

        memo.put(key, Math.max(down, right) + grid[y][x]);
        return memo.get(key);
    }
}