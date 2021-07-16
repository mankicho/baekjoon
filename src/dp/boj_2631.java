package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2631 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] children = new int[n];

        for (int i = 0; i < n; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        int max = 1;

        int[] dp = new int[n];

        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (children[i] > children[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > max) {
                        max = dp[i];
                    }
                }
            }
        }
        System.out.println(n - max);
    }

}