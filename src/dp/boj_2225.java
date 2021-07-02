package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2225 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        long[][] dp = new long[k][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < k; i++) {

        }
        for (int i = 1; i < k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int g = 0; g <= n && j + g <= n; g++) {
                    dp[i][j + g] = (dp[i][j + g] + dp[i - 1][j]) % 1000000000;
                }
            }
        }
        System.out.println(dp[k - 1][n] % 1000000000);
    }
}