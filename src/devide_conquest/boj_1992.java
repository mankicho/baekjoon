package devide_conquest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1992 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        function(arr, 0, n, 0, n);

        System.out.println(sb.toString());

    }

    static void function(int[][] arr, int startRow, int endRow, int startCol, int endCol) {
        int val = arr[startRow][startCol];
        boolean needDevide = false;
        OUTER_LOOP:
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (arr[i][j] != val) {
                    needDevide = true;
                    break OUTER_LOOP;
                }
            }
        }

        if (needDevide) {
            sb.append("(");
            function(arr, startRow, (startRow + endRow) / 2, startCol, (startCol + endCol) / 2);
            function(arr, startRow, (startRow + endRow) / 2, (startCol + endCol) / 2, endCol);
            function(arr, (startRow + endRow) / 2, endRow, startCol, (startCol + endCol) / 2);
            function(arr, (startRow + endRow) / 2, endRow, (startCol + endCol) / 2, endCol);
            sb.append(")");
        } else {
            if (val == 0) {
                sb.append(0);
            } else {
                sb.append(1);
            }
        }
    }
}
