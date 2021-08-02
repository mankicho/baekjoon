package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class BOJ_1328 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int l = arr[1];
        int r = arr[2];

        long[][][] dp = new long[101][101][101];
        dp[1][1][1] = 1;
        dp[2][1][2] = 1;
        dp[2][2][1] = 1;

        int MOD = 1000000007;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= l; j++) {
                for (int k = 1; k <= r; k++) {
                    dp[i][j][k] += dp[i - 1][j][k - 1] % MOD;
                    dp[i][j][k] += dp[i - 1][j - 1][k] % MOD;
                    dp[i][j][k] += dp[i - 1][j][k] * (i - 2) % MOD;
                    dp[i][j][k] %= MOD;
                }
            }
        }

        System.out.println(dp[n][l][r]);

    }


}
