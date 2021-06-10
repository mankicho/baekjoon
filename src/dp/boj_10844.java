package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_10844 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(9);
        } else {
            long[][] dp = new long[101][11];
            for (int i = 1; i <= 9; i++) {
                dp[1][i] = 1;
            }
            System.out.println(function(n,dp) % 1000000000);
        }


    }

    static long function(int n, long[][] dp) {
        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][10] = 0;
            for (int j = 1; j <= 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }


        long sum = 0;
        for (int i = 0; i < 11; i++) {
            sum += dp[n][i] % 1000000000;
        }
        return sum;
    }
}




