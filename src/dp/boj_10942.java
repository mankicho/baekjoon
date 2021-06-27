package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_10942 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
            if (i < dp.length - 1 && arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp.length - i && j + i < dp.length; j++) {
                int k = i + j;
                if (arr[j] == arr[k] && dp[j + 1][k - 1]) {
                    dp[j][k] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (dp[tmp[0] - 1][tmp[1] - 1]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb.toString());
    }


}
