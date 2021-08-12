package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17404 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] houses = new int[n][3];

        for (int i = 0; i < n; i++) {
            houses[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        int[][][] dp = new int[3][n][3];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(dp[i][0], 10000000);
            dp[i][0][i] = houses[0][i];
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < n; j++) {
                if (j == n - 1) {
                    switch (i) {
                        case 0:
                            dp[i][j][1] = Math.min(dp[i][j - 1][0], dp[i][j - 1][2]) + houses[j][1];
                            dp[i][j][2] = Math.min(dp[i][j - 1][0], dp[i][j - 1][1]) + houses[j][2];
                            break;
                        case 1:
                            dp[i][j][0] = Math.min(dp[i][j - 1][1], dp[i][j - 1][2]) + houses[j][0];
                            dp[i][j][2] = Math.min(dp[i][j - 1][0], dp[i][j - 1][1]) + houses[j][2];
                            break;
                        case 2:
                            dp[i][j][0] = Math.min(dp[i][j - 1][1], dp[i][j - 1][2]) + houses[j][0];
                            dp[i][j][1] = Math.min(dp[i][j - 1][0], dp[i][j - 1][2]) + houses[j][1];
                            break;
                    }
                } else {
                    dp[i][j][0] = Math.min(dp[i][j - 1][1], dp[i][j - 1][2]) + houses[j][0];
                    dp[i][j][1] = Math.min(dp[i][j - 1][0], dp[i][j - 1][2]) + houses[j][1];
                    dp[i][j][2] = Math.min(dp[i][j - 1][0], dp[i][j - 1][1]) + houses[j][2];
                }
            }
        }

        int min = 987654321;
        for (int i = 0; i < 3; i++) {
            int[] result = dp[i][n - 1];
            for (int val : result) {
                if (val != 0 && val < min) {
                    min = val;
                }
            }
        }
        System.out.println(min);
    }
}

