package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class BOJ_9084 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int m = Integer.parseInt(br.readLine());

            int[] dp = new int[10001];

            for (int j = 0; j < arr.length; j++) {
                int coin = arr[j];
                dp[coin]++;
                for (int k = 0; k + coin < dp.length && k + coin <= m; k++) {
                    dp[k + coin] += dp[k];
                }
            }
            result.append(dp[m]).append("\n");
        }
        System.out.println(result.toString());
    }

}
