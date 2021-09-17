package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;

public class BOJ_1022 {

    static int r1, c1, r2, c2, maxRow, maxCol;
    static int[][] map;
    static int[][] dir = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };
    static int start = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        r1 = arr[0];
        c1 = arr[1];
        r2 = arr[2];
        c2 = arr[3];

        maxRow = Math.max(Math.abs(r1), Math.abs(r2));
        maxCol = Math.max(Math.abs(c1), Math.abs(c2));
        map = new int[50][5];
        function(2, 0, 0, 1);

        if (0 >= r1 && 0 <= r2 && 0 >= c1 && 0 <= c2) {
            map[-r1][-c1] = 1;
        }
        int len = length(max);
        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                for (int k = 0; k <len - length(map[i][j]); k++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int max = 0;

    static int length(int val) {
        return (int) Math.log10(val);
    }

    static void function(int length, int direction, int row, int col) {
        if (row < -Math.max(maxRow, maxCol) || col < -Math.max(maxRow, maxCol)) {
            return;
        }
        int[] d = dir[direction];

        int endRow = d[0] * (length - 1) + row;
        int endCol = d[1] * (length - 1) + col;
        for (int i = row; endRow < row ? i >= endRow : i <= endRow; i = endRow < row ? i - 1 : i + 1) {
            for (int j = col; endCol < col ? j >= endCol : j <= endCol; j = endCol < col ? j - 1 : j + 1) {
                if ((i >= r1 && i <= r2 && j >= c1 && j <= c2)) {
                    map[i - r1][j - c1] = start;
                    max = start;
                }
                start++;
            }
        }
        int[] tmpD = dir[direction == 3 ? 0 : direction + 1];
        if (direction == 3) {
            function(length + 2, 0, endRow + d[0], endCol + d[1]);
        } else {
            function(length, direction + 1, endRow + tmpD[0], endCol + tmpD[1]);
        }
    }
}