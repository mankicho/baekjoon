package solve.class5;



import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1562 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][][] dp = new long[n + 1][10][1024];

        for (int i = 0; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        dp[1][0][1] = 0;
        int MOD = 1000000000;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 1; k < 1024; k++) {
                    if (j == 0) {
                        dp[i + 1][1][k | 2] += ((dp[i][0][k]) % MOD);
                    } else if (j == 9) {
                        dp[i + 1][8][k | (1 << 8)] += ((dp[i][9][k]) % MOD);
                    } else {
                        dp[i + 1][j - 1][(k | (1 << (j - 1)))] += ((dp[i][j][k]) % MOD);
                        dp[i + 1][j + 1][(k | (1 << (j + 1)))] += ((dp[i][j][k]) % MOD);
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (dp[n][i][1023] % MOD);
        }
        System.out.println(sum% MOD);
    }


}