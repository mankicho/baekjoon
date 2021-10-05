package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2458 {

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        boolean[][] dp = new boolean[n + 1][n + 1];
        boolean[][] dp2 = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
            dp2[i][i] = true;
        }
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];

            dp[a][b] = true;
            dp2[b][a] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (!dp[j][k]) {
                        dp[j][k] = dp[j][i] && dp[i][k];
                    }

                    if (!dp2[j][k]) {
                        dp2[j][k] = dp2[j][i] && dp2[i][k];
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean can = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }

                if (!dp[i][j] && !dp2[i][j]) {
                    can = false;
                    break;
                }
            }
            if (can) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

