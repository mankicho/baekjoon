package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11053 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            int val = arr[i];
            for (int j = 0; j < i; j++) {
                if (val > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
}