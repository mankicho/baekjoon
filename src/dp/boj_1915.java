package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1915 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        String[] square = new String[n];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            square[i] = br.readLine();
            for (int j = 0; j < square[i].length(); j++) {
                if (square[i].charAt(j) == '1') {
                    dp[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < square.length - 1; i++) {
            String line = square[i];

            String secondLine = square[i + 1];
            for (int j = 0; j < line.length() - 1; j++) {
                if (line.charAt(j) == '1'
                        && line.charAt(j + 1) == '1'
                        && secondLine.charAt(j) == '1'
                        && secondLine.charAt(j + 1) == '1'
                ) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        System.out.println(max * max);
    }
}