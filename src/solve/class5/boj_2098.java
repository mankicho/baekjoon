package solve.class5;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2098 {


    // 65
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][(1 << n)];

        int[][] vals = new int[n][n];
        for (int i = 0; i < n; i++) {
            vals[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 20000000);
        }

        int[][] starts = new int[n + 1][(1 << n)];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || vals[i - 1][j - 1] == 0) {
                    continue;
                }

                dp[j][((1 << (i - 1)) | (1 << (j - 1)))] = vals[i - 1][j - 1];
                starts[j][(1 << (i - 1)) | 1 << (j - 1)] = i;
            }
        }


        int idx = 0;
        while (idx < n) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j < (1 << n); j++) {
                    for (int k = 0; k < n; k++) {
                        if ((j & ((1 << k))) != 0 || vals[i - 1][k] == 0) {
                            continue;
                        }

                        if (dp[k + 1][j | (1 << k)] > dp[i][j] + vals[i - 1][k]) {
                            dp[k + 1][j | (1 << k)] = Math.min(dp[k + 1][j | (1 << k)],
                                    dp[i][j] + vals[i - 1][k]);

                            starts[k + 1][j | (1 << k)] = starts[i][j];
                        }


                    }
                }
            }
            idx++;
        }

        int min = 20000000;
        for (int i = 1; i <= n; i++) {
            int start = starts[i][((1 << n) - 1)];
//            System.out.println(Arrays.toString(dp[i]));
//            System.out.println(Arrays.toString(starts[i]));
            if (start != 0 && dp[i][((1 << n) - 1)] + vals[i - 1][start - 1] <
                    min && vals[i - 1][start - 1] != 0) {
                min = dp[i][((1 << n) - 1)] + vals[i - 1][start - 1];
            }
        }

        System.out.println(min);
//        }

    }
}