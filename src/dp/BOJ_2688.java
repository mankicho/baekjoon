package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2688 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[65][10];

        Arrays.fill(dp[1], 1);
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            int n = Integer.parseInt(br.readLine());

            long sum = 0;
            for (int j = 0; j < dp[n].length; j++) {
                sum += dp[n][j];
            }
            result.append(sum).append("\n");
        }
        System.out.println(result.toString());
    }
}