package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_5557 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n][21];

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        11
//        8 3 2 4 8 7 2 4 0 8 8
        int last = arr[arr.length - 1];
        dp[0][arr[0]]++;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i - 1][j] > 0) {
                    if (j - arr[i] >= 0) {
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                    if (j + arr[i] <= 20) {
                        dp[i][j + arr[i]] += dp[i - 1][j];
                    }
                }
            }
        }
        int idx = 0;
        System.out.println(dp[n-2][last]);
    }
}