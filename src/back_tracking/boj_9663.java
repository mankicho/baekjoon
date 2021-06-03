package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_9663 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        int[][] arr = new int[n][n];
        function(arr);

        System.out.println(answer);
    }

    static int answer = 0;

    static int idx = 0;

    static boolean canAttack(int[][] arr, int row, int col) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] == 1) {
                return true;
            }
        }


        for (int i = 0; i < arr.length; i++) {
            if (arr[row][i] == 1) {
                return true;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (row + i < arr.length && col + i < arr.length) {
                if (arr[row + i][col + i] == 1) {
                    return true;
                }
            }

            if (row - i >= 0 && col + i < arr.length) {
                if (arr[row - i][col + i] == 1) {
                    return true;
                }
            }

            if (row + i < arr.length && col - i >= 0) {
                if (arr[row + i][col - i] == 1) {
                    return true;
                }
            }

            if (row - i >= 0 && col - i >= 0) {
                if (arr[row - i][col - i] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    static int queenIdx = 0;

    static void function(int[][] arr) {
        if (queenIdx == arr.length) {
            answer++;
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!canAttack(arr, queenIdx, i)) {
                arr[queenIdx][i] = 1;
                queenIdx++;
                function(arr);
                queenIdx--;
                arr[queenIdx][i] = 0;
            }
        }


    }
}