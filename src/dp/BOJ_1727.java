package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1727 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[] mans = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] womans = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(mans);
        Arrays.sort(womans);
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == j) {
                    dp[i][j] = dp[i-1][j-1] + Math.abs(mans[i-1]- womans[i-1]);
                } else if (i > j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(mans[i - 1] - womans[j - 1]));
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(mans[i - 1] - womans[j - 1]));
                }
            }
        }
        System.out.println(dp[n][m]);

    }

}