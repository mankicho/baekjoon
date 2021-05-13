package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9461 {
    public static void main(String[] args) throws Exception {
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;
        dp[7] = 4;
        dp[8] = 5;
        dp[9] = 7;
        dp[10] = 9;

        for (int i = 11; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        System.out.println(Arrays.toString(dp));

        System.out.println(dp[100]);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            int k = Integer.parseInt(br.readLine());

            System.out.println(dp[k]);
        }
    }

}
