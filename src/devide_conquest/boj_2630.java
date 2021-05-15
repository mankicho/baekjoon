package devide_conquest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2630 {
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        function(arr, 0, n, 0, n);

        

    }

    static void function(int[][] arr, int startRow, int endRow, int startCol, int endCol) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int value = arr[startRow][startCol];
        boolean needDevide = false;
        LOOP:
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
//                br.readLine();
                if (arr[i][j] != value) {
                    needDevide = true;
                    break LOOP;
                }
            }
        }

        if (needDevide) {
            function(arr, startRow, (startRow + endRow) / 2, startCol, (startCol + endCol) / 2);
            function(arr, (startRow + endRow) / 2, endRow, startCol, (startCol + endCol) / 2);
            function(arr, startRow, (startRow + endRow) / 2, (startCol + endCol) / 2, endCol);
            function(arr, (startRow + endRow) / 2, endRow, (startCol + endCol) / 2, endCol);
        } else {
            if (value == 0) {
                white++;
            } else {
                blue++;
            }
        }
    }
}
