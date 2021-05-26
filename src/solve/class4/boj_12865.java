package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class boj_12865 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int weight = arr[0];
            int value = arr[1];

            if (weight > k) {
                continue;
            }

            dp[i][weight] = value;

            for (int j = 0; j < i; j++) {
                int[] tmpArr = dp[j];
                for (int l = 0; l < tmpArr.length; l++) {
                    if (tmpArr[l] != 0 && l + weight <= k) {
                        dp[i][l + weight] = Math.max(dp[j][l] + dp[i][weight], dp[i][l + weight]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        System.out.println(max);
    }
}


