package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2749 {

    static final int MOD = 1000000;

    static long[][] answer = new long[][]{
            {1, 1}, {1, 1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        if (n <= 2) {
            System.out.println(1);
            return;
        }
        n -= 2;
        long[][][] dp = new long[65][2][2];

        dp[0] = new long[][]{
                {1, 1}, {1, 0}
        };

        for (int i = 1; i <= 64; i++) {
            dp[i] = multipleMatrix(dp[i - 1], dp[i - 1]);
        }

        while (n > 0) {
            n = function(n, dp);
        }

        System.out.println(answer[1][0] % MOD);
    }

    static long function(long n, long[][][] dp) {
        int max = 63;

        while (max > 0 && (long) Math.pow(2, max) > n) {
            max--;
        }
        answer = multipleMatrix(answer, dp[max]);

        return n - (long) Math.pow(2, max);
    }

    static long[][] multipleMatrix(long[][] matrix, long[][] matrix2) {
        long[][] tmp = new long[2][2];

        tmp[0][0] = (matrix[0][0] * matrix2[0][0]) % MOD + (matrix[0][1] * matrix2[1][0]) % MOD;
        tmp[0][1] = (matrix[0][0] * matrix2[0][1]) % MOD + (matrix[0][1] * matrix2[1][1]) % MOD;
        tmp[1][0] = (matrix[1][0] * matrix2[0][0]) % MOD + (matrix[1][1] * matrix2[1][0]) % MOD;
        tmp[1][1] = (matrix[1][0] * matrix2[0][1]) % MOD + (matrix[1][1] * matrix2[1][1]) % MOD;

        return tmp;
    }
}